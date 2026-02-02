package com.example.modularmonolith.modules.order.application

import com.example.modularmonolith.modules.catalog.application.GetProductService
import com.example.modularmonolith.modules.order.domain.Order
import com.example.modularmonolith.modules.order.domain.OrderItem
import com.example.modularmonolith.modules.order.domain.OrderRepository
import com.example.modularmonolith.modules.order.domain.OrderStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlaceOrderService(
    private val orderRepository: OrderRepository,
    private val getProductService: GetProductService
) {
    fun execute(input: PlaceOrderInput): String {
        // Validate items
        val orderItems = input.items.map { item ->
            val product = getProductService.execute(item.productId)
                ?: throw IllegalArgumentException("Product with ID ${item.productId} not found")

            OrderItem(
                productId = product.id,
                quantity = item.quantity
            )
        }

        // Create Order
        val orderId = UUID.randomUUID().toString()
        val order = Order(
            id = orderId,
            customerId = input.customerId,
            items = orderItems,
            status = OrderStatus.CREATED
        )

        // Save Order
        orderRepository.save(order)

        return orderId
    }
}

data class PlaceOrderInput(
    val customerId: String,
    val items: List<PlaceOrderItemInput>
)

data class PlaceOrderItemInput(
    val productId: String,
    val quantity: Int
)
