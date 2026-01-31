package com.example.modularmonolith.modules.catalog.presentation

import com.example.modularmonolith.modules.catalog.application.GetProductService
import com.example.modularmonolith.modules.catalog.domain.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/catalog/products")
internal class CatalogController(
    private val getProductService: GetProductService
) {

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String): Product? {
        return getProductService.execute(id)
    }
}
