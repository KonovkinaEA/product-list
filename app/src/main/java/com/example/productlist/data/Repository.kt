package com.example.productlist.data

import com.example.productlist.data.model.LoadingOptions
import com.example.productlist.data.model.Product
import com.example.productlist.data.model.ProductsDataState
import kotlinx.coroutines.flow.StateFlow

interface Repository {
    val productsDataState: StateFlow<ProductsDataState>
    suspend fun getProduct(id: Int): Product?
    suspend fun dataLoad()
    suspend fun loadNextData(option: LoadingOptions)
}
