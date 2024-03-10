package com.example.productlist.data.api.model

import java.security.SecureRandom

data class ProductItemServer(
    val id: Int = SecureRandom().nextInt(),
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val brand: String = "",
    val category: String = "",
    val thumbnail: String = "",
    val images: List<String> = emptyList()
)
