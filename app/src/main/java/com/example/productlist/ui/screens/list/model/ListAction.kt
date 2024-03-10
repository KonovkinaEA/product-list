package com.example.productlist.ui.screens.list.model

import com.example.productlist.data.model.Product

sealed class ListAction {
    data class OpenProduct(val product: Product): ListAction()
}
