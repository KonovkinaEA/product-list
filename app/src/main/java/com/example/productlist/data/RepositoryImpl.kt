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
            newPrice = 477.85,
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
            newPrice = 1556.26,
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
            newPrice = 11.91,
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
        ),
        Product(
            id = 16,
            title = "Hyaluronic Acid Serum",
            description = "L'OrÃ©al Paris introduces Hyaluron Expert Replumping Serum formulated with 1.5% Hyaluronic Acid",
            price = 19,
            discountPercentage = 13.31,
            newPrice = 16.47,
            rating = 4.83,
            stock = 110,
            brand = "L'Oreal Paris",
            category = Category.SKINCARE,
            thumbnail = "https://cdn.dummyjson.com/product-images/16/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/16/1.png",
                "https://cdn.dummyjson.com/product-images/16/2.webp",
                "https://cdn.dummyjson.com/product-images/16/3.jpg",
                "https://cdn.dummyjson.com/product-images/16/4.jpg",
                "https://cdn.dummyjson.com/product-images/16/thumbnail.jpg"
            )
        ),
        Product(
            id = 22,
            title = "Elbow Macaroni - 400 gm",
            description = "Product details of Bake Parlor Big Elbow Macaroni - 400 gm",
            price = 14,
            discountPercentage = 15.58,
            newPrice = 11.82,
            rating = 4.57,
            stock = 146,
            brand = "Bake Parlor Big",
            category = Category.GROCERIES,
            thumbnail = "https://cdn.dummyjson.com/product-images/22/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/22/1.jpg",
                "https://cdn.dummyjson.com/product-images/22/2.jpg",
                "https://cdn.dummyjson.com/product-images/22/3.jpg"
            )
        ),
        Product(
            id = 26,
            title = "Plant Hanger For Home",
            description = "Boho Decor Plant Hanger For Home Wall Decoration Macrame Wall Hanging Shelf",
            price = 41,
            discountPercentage = 17.86,
            newPrice = 33.68,
            rating = 4.08,
            stock = 131,
            brand = "Boho Decor",
            category = Category.HOME_DECORATION,
            thumbnail = "https://cdn.dummyjson.com/product-images/26/thumbnail.jpg",
            images = listOf(
                "https://cdn.dummyjson.com/product-images/26/1.jpg",
                "https://cdn.dummyjson.com/product-images/26/2.jpg",
                "https://cdn.dummyjson.com/product-images/26/3.jpg",
                "https://cdn.dummyjson.com/product-images/26/4.jpg",
                "https://cdn.dummyjson.com/product-images/26/5.jpg",
                "https://cdn.dummyjson.com/product-images/26/thumbnail.jpg"
            )
        )
    )
}
