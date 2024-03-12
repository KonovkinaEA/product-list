package com.example.productlist.ui.screens.list.model

import com.example.productlist.data.model.Category

sealed class ListAction {
    data class OpenProduct(val id: Int) : ListAction()
    data class ReloadData(val category: Category) : ListAction()
    data object PreviousPage : ListAction()
    data object NextPage : ListAction()
}
