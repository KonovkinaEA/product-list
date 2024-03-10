package com.example.productlist.data

import com.example.productlist.data.api.ApiService
import com.example.productlist.data.api.model.ProductsListServer
import com.example.productlist.data.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    override val products: StateFlow<List<Product>>
        get() = _products.asStateFlow()

    override suspend fun loadProductsData(skipFirst: Int) = withContext(ioDispatcher) {
        try {
            val response = apiService.getProductsData(skipFirst, PRODUCTS_COUNT_PER_PAGE)
            if (response.isSuccessful) {
                val dataFromServer = response.body() as ProductsListServer
                val productsList = dataFromServer.products?.map { Product.fromServerModel(it) }
                if (!productsList.isNullOrEmpty()) {
                    _products.update { productsList }
                }
            } else {
                // TODO
            }
        } catch (e: Exception) {
            // TODO
        }
    }

    companion object {

        private const val PRODUCTS_COUNT_PER_PAGE = 20
    }
}
