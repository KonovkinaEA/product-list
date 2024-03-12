package com.example.productlist.data.api

import com.example.productlist.data.api.model.ProductsListServer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("products")
    suspend fun getProductsData(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Response<ProductsListServer>

    @GET("products/category/{category}")
    suspend fun getProductsDataWithFilter(
        @Path("category") category: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Response<ProductsListServer>
}
