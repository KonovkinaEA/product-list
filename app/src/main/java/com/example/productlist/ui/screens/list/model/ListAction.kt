package com.example.productlist.ui.screens.list.model

sealed class ListAction {
    data class OpenProduct(val id: Int) : ListAction()
    data object PreviousPage : ListAction()
    data object NextPage : ListAction()
}
