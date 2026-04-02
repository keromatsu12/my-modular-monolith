package com.example.modularmonolith.modules.order.infrastructure

import com.example.modularmonolith.modules.order.domain.Order
import com.example.modularmonolith.modules.order.domain.OrderRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
internal class InMemoryOrderRepository : OrderRepository {
    private val orders = ConcurrentHashMap<String, Order>()

    override fun save(order: Order) {
        orders[order.id] = order
    }

    override fun findById(id: String): Order? {
        return orders[id]
    }
}
