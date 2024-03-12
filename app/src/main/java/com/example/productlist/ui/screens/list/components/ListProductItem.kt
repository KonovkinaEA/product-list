package com.example.productlist.ui.screens.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.productlist.R
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.list.model.ListAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview
import com.example.productlist.ui.util.products

@ExperimentalMaterial3Api
@Composable
fun ProductItem(product: Product, onAction: (ListAction) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExtendedTheme.colors.backSecondary
        ),
        onClick = { onAction(ListAction.OpenProduct(product.id)) }
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = "Product item",
            placeholder = painterResource(R.drawable.image_icon),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 7.dp)
                .padding(top = 13.dp, bottom = 7.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = ExtendedTheme.colors.labelPrimary
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = product.brand,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = ExtendedTheme.colors.labelSecondary
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = ExtendedTheme.colors.labelTertiary
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Price(product = product)
            Spacer(modifier = Modifier.height(5.dp))
            Rating(rating = product.rating)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ProductItemWithDiscountPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ProductItem(product = products.first(), onAction = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ProductItemWithoutDiscountPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ProductItem(product = products.first().copy(discountPercentage = 0.0), onAction = {})
    }
}
