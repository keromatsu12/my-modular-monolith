package com.example.modularmonolith.modules.catalog.application

import com.example.modularmonolith.modules.catalog.domain.Product

interface ProductCatalog {
    fun getProduct(id: String): Product?
}
