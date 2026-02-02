package com.example.modularmonolith.modules.order.presentation

import com.example.modularmonolith.modules.order.application.PlaceOrderInput
import com.example.modularmonolith.modules.order.application.PlaceOrderItemInput
import com.example.modularmonolith.modules.order.application.PlaceOrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
internal class OrderController(
    private val placeOrderService: PlaceOrderService
) {

    @PostMapping
    fun placeOrder(@RequestBody request: PlaceOrderRequest): PlaceOrderResponse {
        val input = PlaceOrderInput(
            customerId = request.customerId,
            items = request.items.map {
                PlaceOrderItemInput(it.productId, it.quantity)
            }
        )
        val orderId = placeOrderService.execute(input)
        return PlaceOrderResponse(orderId)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}

data class PlaceOrderRequest(
    val customerId: String,
    val items: List<PlaceOrderItemRequest>
)

data class PlaceOrderItemRequest(
    val productId: String,
    val quantity: Int
)

data class PlaceOrderResponse(
    val orderId: String
)
