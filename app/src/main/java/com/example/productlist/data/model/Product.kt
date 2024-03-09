package com.example.productlist.data.model

data class Product(
    val id: Int,
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val brand: String = "",
    val category: Category = Category.DEFAULT,
    val thumbnail: String = "",
    val images: List<String> = emptyList()
)
