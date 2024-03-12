package com.example.productlist.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Repository
import com.example.productlist.data.model.LoadingOptions
import com.example.productlist.data.model.ProductsDataState
import com.example.productlist.ui.screens.list.model.ListAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _openProductId = Channel<Int>()
    val openProductId = _openProductId.receiveAsFlow()

    val productsDataState: StateFlow<ProductsDataState> = repository.productsDataState

    init {
        viewModelScope.launch { repository.dataLoad() }
    }

    fun onAction(action: ListAction) {
        when (action) {
            ListAction.NextPage -> loadData(LoadingOptions.NEXT_PRODUCTS)
            ListAction.PreviousPage -> loadData(LoadingOptions.PREV_PRODUCTS)
            is ListAction.OpenProduct -> {
                viewModelScope.launch { _openProductId.send(action.id) }
            }
        }
    }

    private fun loadData(options: LoadingOptions) {
        viewModelScope.launch { repository.loadNextData(options) }
    }
}
