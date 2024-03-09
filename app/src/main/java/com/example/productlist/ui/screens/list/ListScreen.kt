package com.example.productlist.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.list.components.ProductItem
import com.example.productlist.ui.screens.list.model.ListUiState
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    ListScreenContent(uiState)
}

@Composable
private fun ListScreenContent(uiState: ListUiState) {
    val listState = rememberLazyListState()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.products, key = { it.id }) {
                    ProductItem(product = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    val products = listOf(
        Product(id = 0),
        Product(id = 1),
        Product(id = 2)
    )

    ProductListTheme(darkTheme = darkTheme) {
        ListScreenContent(
            uiState = ListUiState(products)
        )
    }
}
