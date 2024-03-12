package com.example.productlist.data.model

import com.example.productlist.data.api.model.ProductItemServer
import java.security.SecureRandom
import kotlin.math.roundToInt

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
