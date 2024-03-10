package com.example.productlist.data.model

import com.example.productlist.data.api.model.ProductItemServer
import java.security.SecureRandom
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
            val price = product.price ?: 0
            val discountPercentage = product.discountPercentage ?: 0.0

            return Product(
                id = product.id ?: SecureRandom().nextInt(),
                title = product.title ?: "",
                description = product.description ?: "",
                price = price,
                discountPercentage = discountPercentage,
                newPrice = (price * (100 - discountPercentage)).roundToInt() / 100.0,
                rating = product.rating ?: 0.0,
                stock = product.stock ?: 0,
                brand = product.brand ?: "",
                category = Category.fromValue(product.category ?: ""),
                thumbnail = product.thumbnail ?: "",
                images = product.images ?: emptyList()
            )
        }
    }
}
