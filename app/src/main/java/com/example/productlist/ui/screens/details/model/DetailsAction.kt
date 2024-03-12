package com.example.productlist.ui.screens.details.model

sealed class DetailsAction {
    data object CloseDetails : DetailsAction()
}