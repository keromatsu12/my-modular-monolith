package com.example.modularmonolith.modules.order.domain

data class Order(
    val id: String,
    val customerId: String,
    val items: List<OrderItem>,
    val status: OrderStatus
)

data class OrderItem(
    val productId: String,
    val quantity: Int
)

enum class OrderStatus {
    CREATED,
    COMPLETED,
    CANCELLED
}
