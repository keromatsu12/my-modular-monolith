package com.example.modularmonolith.modules.catalog.application

import com.example.modularmonolith.modules.catalog.domain.Product
import com.example.modularmonolith.modules.catalog.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
internal class GetProductService(
    private val productRepository: ProductRepository
) : ProductCatalog {
    fun execute(id: String): Product? {
        return productRepository.findById(id)
    }

    override fun getProduct(id: String): Product? {
        return execute(id)
    }
}
