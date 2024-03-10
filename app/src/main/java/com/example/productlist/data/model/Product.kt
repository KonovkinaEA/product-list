package com.example.productlist.data.model

import com.example.productlist.data.api.model.ProductItemServer
import kotlin.math.roundToInt

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val newPrice: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: Category,
    val thumbnail: String,
    val images: List<String>
) {

    companion object {

        fun fromServerModel(product: ProductItemServer): Product {
            return Product(
                id = product.id,
                title = product.title,
                description = product.description,
                price = product.price,
                discountPercentage = product.discountPercentage,
                newPrice = (product.price * (100 - product.discountPercentage)).roundToInt() / 100.0,
                rating = product.rating,
                stock = product.stock,
                brand = product.brand,
                category = Category.fromValue(product.category),
                thumbnail = product.thumbnail,
                images = product.images
            )
        }
    }
}
