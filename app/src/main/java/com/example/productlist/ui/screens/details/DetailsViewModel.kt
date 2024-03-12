package com.example.productlist.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.data.Repository
import com.example.productlist.data.model.Product
import com.example.productlist.ui.navigation.Details
import com.example.productlist.ui.screens.details.model.DetailsAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(Product())
    val uiState = _uiState.asStateFlow()

    private val _closeDetails = Channel<Boolean>()
    val closeDetails = _closeDetails.receiveAsFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(Details.id)?.toIntOrNull()?.let {
                repository.getProduct(it)?.let { product ->
                    _uiState.update { product }
                }
            }
        }
    }

    fun onAction(action: DetailsAction) {
        when (action) {
            DetailsAction.CloseDetails -> viewModelScope.launch {
                _closeDetails.send(true)
            }
        }
    }
}
