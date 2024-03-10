package com.example.productlist.ui.util

import com.example.productlist.data.model.Category
import com.example.productlist.data.model.Product

val products = listOf(
    Product(
        id = 0,
        title = "title1",
        description = "description1",
        price = 10,
        discountPercentage = 12.5,
        newPrice = 8.75,
        rating = 4.85,
        stock = 0,
        brand = "brand1",
        category = Category.DEFAULT,
        thumbnail = "thumbnail1",
        images = emptyList()
    ),
    Product(
        id = 1,
        title = "title2",
        description = "description2",
        price = 10,
        discountPercentage = 12.5,
        newPrice = 8.75,
        rating = 4.85,
        stock = 0,
        brand = "brand2",
        category = Category.DEFAULT,
        thumbnail = "thumbnail2",
        images = emptyList()
    ),
    Product(
        id = 2,
        title = "title3",
        description = "description3",
        price = 10,
        discountPercentage = 12.5,
        newPrice = 8.75,
        rating = 4.85,
        stock = 0,
        brand = "brand3",
        category = Category.DEFAULT,
        thumbnail = "thumbnail3",
        images = emptyList()
    )
)
