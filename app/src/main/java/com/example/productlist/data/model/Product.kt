package com.example.productlist.data.model

import java.security.SecureRandom

data class Product(
    val id: Int = SecureRandom().nextInt(),
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val discountPercentage: Double = 0.0,
    val newPrice: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val brand: String = "",
    val category: Category = Category.DEFAULT,
    val thumbnail: String = "",
    val images: List<String> = emptyList()
)
