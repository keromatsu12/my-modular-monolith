package com.example.modularmonolith.modules.catalog.domain

data class Product(
    val id: String,
    val name: String,
    val price: Int
)

interface ProductRepository {
    fun findById(id: String): Product?
}
