package com.example.productlist.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.data.model.ProductsDataState
import com.example.productlist.ui.screens.list.components.ListTopAppBar
import com.example.productlist.ui.screens.list.components.ProductItem
import com.example.productlist.ui.screens.list.model.ListAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview
import com.example.productlist.ui.util.products

@Composable
fun ListScreen(viewModel: ListViewModel = hiltViewModel()) {
    val state by viewModel.productsDataState.collectAsState()

    ListScreenContent(state, viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreenContent(state: ProductsDataState, onAction: (ListAction) -> Unit) {
    Scaffold(
        topBar = { ListTopAppBar(state.isFirstPage, state.isLastPage, onAction) }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(ExtendedTheme.colors.backPrimary)
                .padding(paddingValues)
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp,
        ) {
            items(state.products) { product ->
                ProductItem(product, onAction)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ListScreenContent(
            state = ProductsDataState(
                products = products,
                isFirstPage = false,
                isLastPage = false
            ),
            onAction = {}
        )
    }
}
