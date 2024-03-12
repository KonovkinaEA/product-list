package com.example.productlist.data.model

data class ProductsDataState(
    val errorOnLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val isFirstPage: Boolean = true,
    val isLastPage: Boolean = true
)
