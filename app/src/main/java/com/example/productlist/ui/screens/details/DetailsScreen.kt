package com.example.productlist.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.details.components.BackgroundCard
import com.example.productlist.ui.screens.details.components.DetailsTopAppBar
import com.example.productlist.ui.screens.details.components.ImageCarousel
import com.example.productlist.ui.screens.details.components.PriceCard
import com.example.productlist.ui.screens.details.components.RatingCard
import com.example.productlist.ui.screens.details.model.DetailsAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview
import com.example.productlist.ui.util.products

@Composable
fun DetailsScreen(onProductClose: () -> Unit, viewModel: DetailsViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.closeDetails.collect { onProductClose() }
    }

    DetailsScreenContent(state, viewModel::onAction)
}

@Composable
private fun DetailsScreenContent(product: Product, onAction: (DetailsAction) -> Unit) {
    Scaffold(
        topBar = { DetailsTopAppBar(onAction) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            ImageCarousel(images = product.images)
            Spacer(modifier = Modifier.height(10.dp))

            BackgroundCard { PriceCard(product) }
            Spacer(modifier = Modifier.height(10.dp))

            BackgroundCard {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = ExtendedTheme.colors.labelPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = ExtendedTheme.colors.labelSecondary
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.displaySmall.copy(
                            color = ExtendedTheme.colors.labelTertiary
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    RatingCard(product.rating)
                }
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
        DetailsScreenContent(product = products.first(), onAction = {})
    }
}
