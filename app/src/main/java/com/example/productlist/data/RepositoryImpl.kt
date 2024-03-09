package com.example.productlist.data

import com.example.productlist.data.model.Category
import com.example.productlist.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    override val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()

    init {
        _products.update { hardcodedProducts() }
    }

    private fun hardcodedProducts() = listOf(
        Product(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            discountPercentage = 12.96,
            rating = 4.69,
            stock = 94,
            brand = "Apple",
            category = Category.SMARTPHONES,
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
        ),
        Product(
            id = 6,
            title = "MacBook Pro",
            description = "MacBook Pro 2021 with mini-LED display may launch between September, November",
            price = 1749,
            discountPercentage = 11.02,
            rating = 4.57,
            stock = 83,
            brand = "Apple",
            category = Category.LAPTOPS,
            thumbnail = "https://cdn.dummyjson.com/product-images/6/thumbnail.png",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/6/1.png",
                "https://cdn.dummyjson.com/product-images/6/2.jpg",
                "https://cdn.dummyjson.com/product-images/6/3.png",
                "https://cdn.dummyjson.com/product-images/6/4.jpg"
            )
        ),
        Product(
            id = 11,
            title = "perfume Oil",
            description = "Mega Discount, Impression of Acqua Di Gio by GiorgioArmani concentrated attar perfume Oil",
            price = 13,
            discountPercentage = 8.4,
            rating = 4.26,
            stock = 65,
            brand = "Impression of Acqua Di Gio",
            category = Category.FRAGRANCES,
            thumbnail = "https://cdn.dummyjson.com/product-images/11/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/11/1.jpg",
                "https://cdn.dummyjson.com/product-images/11/2.jpg",
                "https://cdn.dummyjson.com/product-images/11/3.jpg",
                "https://cdn.dummyjson.com/product-images/11/thumbnail.jpg"
            )
        )
    )
}
