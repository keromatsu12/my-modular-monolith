package com.example.modularmonolith.modules.order.domain

interface OrderRepository {
    fun save(order: Order)
    fun findById(id: String): Order?
}
