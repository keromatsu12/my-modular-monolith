package com.example.modularmonolith.modules.catalog.infrastructure

import com.example.modularmonolith.modules.catalog.domain.Product
import com.example.modularmonolith.modules.catalog.domain.ProductRepository
import org.springframework.stereotype.Repository

@Repository
internal class InMemoryProductRepository : ProductRepository {
    private val products = mapOf(
        "1" to Product("1", "Product A", 100),
        "2" to Product("2", "Product B", 200)
    )

    override fun findById(id: String): Product? {
        return products[id]
    }
}
