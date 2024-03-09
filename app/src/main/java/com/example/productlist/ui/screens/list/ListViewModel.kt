package com.example.productlist.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Repository
import com.example.productlist.ui.screens.list.model.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModelScope.launch {
            repository.products.collectLatest { products ->
                _uiState.update {
                    uiState.value.copy(products = products)
                }
            }
        }
    }
}
