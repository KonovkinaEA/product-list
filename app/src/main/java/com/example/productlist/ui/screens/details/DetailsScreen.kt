package com.example.productlist.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.details.components.ImageCarousel
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview
import com.example.productlist.ui.util.products

@Composable
fun DetailsScreen(
    onProductClose: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    DetailsScreenContent(state)
}

@Composable
private fun DetailsScreenContent(state: Product) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ImageCarousel(state.images)
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = ExtendedTheme.colors.labelPrimary
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = state.brand,
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = ExtendedTheme.colors.labelSecondary
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = state.description,
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = ExtendedTheme.colors.labelTertiary
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        DetailsScreenContent(state = products.first())
    }
}
