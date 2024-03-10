package com.example.productlist.data

import com.example.productlist.data.model.ProductsDataState
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val productsDataState: StateFlow<ProductsDataState>
    suspend fun loadProductsData(nextElements: Boolean)
}
