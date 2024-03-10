package com.example.productlist.data

import com.example.productlist.data.api.ApiService
import com.example.productlist.data.api.model.ProductsListServer
import com.example.productlist.data.model.Product
import com.example.productlist.data.model.ProductsDataState
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

    private var skipFirst: Int = 0

    private val _productsDataState = MutableStateFlow(ProductsDataState())
    override val productsDataState: StateFlow<ProductsDataState>
        get() = _productsDataState.asStateFlow()

    override suspend fun loadProductsData(nextElements: Boolean) = withContext(ioDispatcher) {
        if (!nextElements) skipFirst -= PRODUCTS_COUNT_PER_PAGE * 2
        try {
            val response = apiService.getProductsData(skipFirst, PRODUCTS_COUNT_PER_PAGE)
            if (response.isSuccessful) {
                val dataFromServer = response.body() as ProductsListServer
                val productsList = dataFromServer.products.map { Product.fromServerModel(it) }
                if (productsList.isNotEmpty()) {
                    _productsDataState.update {
                        productsDataState.value.copy(
                            products = productsList,
                            isFirstPage = dataFromServer.skip == 0,
                            isLastPage = dataFromServer.total <= dataFromServer.skip + dataFromServer.limit
                        )
                    }
                    skipFirst = dataFromServer.skip + PRODUCTS_COUNT_PER_PAGE
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
