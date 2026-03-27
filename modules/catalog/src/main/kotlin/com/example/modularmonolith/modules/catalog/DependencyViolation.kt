package com.example.modularmonolith.modules.catalog

import com.example.modularmonolith.modules.order.domain.Order
import org.springframework.stereotype.Component

@Component
class DependencyViolation {
    fun doSomething(order: Order) {
        println(order.id)
    }
}
