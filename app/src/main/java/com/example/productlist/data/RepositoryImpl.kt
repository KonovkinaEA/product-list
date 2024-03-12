package com.example.productlist.data

import com.example.productlist.data.api.ApiService
import com.example.productlist.data.api.model.ProductsListServer
import com.example.productlist.data.model.LoadingOptions
import com.example.productlist.data.model.Product
import com.example.productlist.data.model.ProductsDataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    override suspend fun getProduct(id: Int) =
        _productsDataState.value.products.firstOrNull { it.id == id }

    override suspend fun dataLoad() {
        try {
            val response = apiService.getProductsData(skipFirst, PRODUCTS_COUNT_PER_PAGE)
            if (response.isSuccessful) {
                val dataFromServer = response.body() as ProductsListServer
                val productsList = dataFromServer.products.map { Product.fromServerModel(it) }
                if (productsList.isNotEmpty()) {
                    _productsDataState.value = ProductsDataState(
                        products = productsList,
                        isFirstPage = dataFromServer.skip == 0,
                        isLastPage = dataFromServer.total <= dataFromServer.skip + dataFromServer.limit
                    )
                }
            } else {
                _productsDataState.value = ProductsDataState(errorOnLoading = true)
            }
        } catch (e: Exception) {
            _productsDataState.value = ProductsDataState(errorOnLoading = true)
        }
    }

    override suspend fun loadNextData(option: LoadingOptions) = withContext(ioDispatcher) {
        if (option == LoadingOptions.NEXT_PRODUCTS) {
            skipFirst += PRODUCTS_COUNT_PER_PAGE
        } else {
            skipFirst -= PRODUCTS_COUNT_PER_PAGE
        }
        dataLoad()
    }

    companion object {

        private const val PRODUCTS_COUNT_PER_PAGE = 20
    }
}
