package com.example.productlist.data

import com.example.productlist.MainCoroutineRule
import com.example.productlist.data.api.ApiService
import com.example.productlist.data.api.model.ProductItemServer
import com.example.productlist.data.api.model.ProductsListServer
import com.example.productlist.data.model.Product
import com.example.productlist.data.model.ProductsDataState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.robolectric.util.ReflectionHelpers
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {

    private val productListServer = mutableListOf<ProductItemServer>()
    private val productListResponse: ProductsListServer = mockk {
        every { products } returns productListServer
        every { total } returns TOTAL_PRODUCTS_COUNT
        every { skip } returns SKIP_FIRST_ELEMENTS
        every { limit } returns LIMIT_PRODUCTS_COUNT
    }
    private val response: Response<ProductsListServer> = mockk {
        every { isSuccessful } returns true
        every { body() } returns productListResponse
    }
    private val apiService = mockk<ApiService>(relaxed = true) {
        coEvery { getProductsData(any(), any()) } returns response
    }

    private val products = mutableListOf<Product>()
    private val productsData: ProductsDataState = mockk(relaxed = true) {
        every { products } returns this@RepositoryImplTest.products
    }
    private val _productsData: MutableStateFlow<ProductsDataState> = mockk(relaxed = true) {
        every { value } returns productsData
    }

    private val repository = RepositoryImpl(apiService, mockk())

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repeat(10) { products.add(mockk(relaxed = true)) }
        repeat(10) { productListServer.add(mockk(relaxed = true)) }

        ReflectionHelpers.setField(repository, PRODUCTS_DATA_STATE_FIELD_NAME, _productsData)
    }

    @Test
    fun testGetProduct() = runTest {
        val newItem: Product = mockk {
            every { id } returns ITEM_ID
        }
        products.add(newItem)
        products.shuffle()

        val item = repository.getProduct(ITEM_ID)
        advanceUntilIdle()

        Assert.assertEquals(newItem, item)
    }

    @Test
    fun testSuccessfulDataLoad() = runTest {
        repository.loadData()
        val state = repository.productsDataState.value
        advanceUntilIdle()

        coVerify { apiService.getProductsData(SKIP_FIRST_ELEMENTS, LIMIT_PRODUCTS_COUNT) }
        Assert.assertTrue(state.products.isNotEmpty())
    }

    @Test
    fun testUnsuccessfulDataLoad() = runTest {
        coEvery { apiService.getProductsData(any(), any()) } throws mockk()

        repository.loadData()
        val state = repository.productsDataState.value
        advanceUntilIdle()

        Assert.assertTrue(state.errorOnLoading)
    }

    companion object {

        private const val TOTAL_PRODUCTS_COUNT = 10
        private const val SKIP_FIRST_ELEMENTS = 0
        private const val LIMIT_PRODUCTS_COUNT = 20
        private const val ITEM_ID = 1

        private const val PRODUCTS_DATA_STATE_FIELD_NAME = "_productsDataState"
    }
}
