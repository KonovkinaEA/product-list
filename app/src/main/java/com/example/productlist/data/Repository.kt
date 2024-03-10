package com.example.productlist.data

import com.example.productlist.data.model.Product
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val products: StateFlow<List<Product>>
    suspend fun loadProductsData(skipFirst: Int)
}
