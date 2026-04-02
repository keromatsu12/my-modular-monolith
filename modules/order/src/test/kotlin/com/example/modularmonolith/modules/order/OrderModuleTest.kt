package com.example.modularmonolith.modules.order

import com.example.modularmonolith.modules.catalog.application.ProductCatalog
import com.example.modularmonolith.modules.catalog.domain.Product
import com.example.modularmonolith.modules.order.application.PlaceOrderInput
import com.example.modularmonolith.modules.order.application.PlaceOrderItemInput
import com.example.modularmonolith.modules.order.application.PlaceOrderService
import com.example.modularmonolith.modules.order.infrastructure.InMemoryOrderRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class OrderModuleTest {

    private val orderRepository = InMemoryOrderRepository()
    private val productCatalog = mock(ProductCatalog::class.java)
    private val placeOrderService = PlaceOrderService(orderRepository, productCatalog)

    @Test
    fun `should place order successfully`() {
        // Given
        val productId = "1"
        `when`(productCatalog.getProduct(productId)).thenReturn(Product(productId, "Test Product", 100))

        val input = PlaceOrderInput(
            customerId = "customer-1",
            items = listOf(PlaceOrderItemInput(productId, 2))
        )

        // When
        val orderId = placeOrderService.execute(input)

        // Then
        assertNotNull(orderId)
        assertNotNull(orderRepository.findById(orderId))
    }

    @Test
    fun `should fail if product not found`() {
        // Given
        val productId = "999"
        `when`(productCatalog.getProduct(productId)).thenReturn(null)

        val input = PlaceOrderInput(
            customerId = "customer-1",
            items = listOf(PlaceOrderItemInput(productId, 2))
        )

        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            placeOrderService.execute(input)
        }
    }
}
