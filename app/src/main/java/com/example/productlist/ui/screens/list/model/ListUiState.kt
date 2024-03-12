package com.example.productlist.ui.screens.list.model

import com.example.productlist.data.model.Product

data class ListUiState(
    val products: List<Product> = emptyList()
)
