package com.example.productlist.data.api.model

data class ProductsListServer(
    val products: List<ProductItemServer> = emptyList(),
    val total: Int = 0,
    val skip: Int = 0,
    val limit: Int = 0
)
