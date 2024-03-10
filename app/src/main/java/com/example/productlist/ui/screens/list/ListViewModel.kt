package com.example.productlist.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Repository
import com.example.productlist.data.model.ProductsDataState
import com.example.productlist.ui.screens.list.model.ListAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val productsDataState: StateFlow<ProductsDataState> = repository.productsDataState

    init {
        loadData(true)
    }

    fun onAction(action: ListAction) {
        when (action) {
            ListAction.NextPage -> loadData(true)
            ListAction.PreviousPage -> loadData(false)
            is ListAction.OpenProduct -> {}
        }
    }

    private fun loadData(nextElements: Boolean) {
        viewModelScope.launch { repository.loadProductsData(nextElements) }
    }
}
