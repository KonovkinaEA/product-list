package com.example.productlist.ui.screens.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.productlist.R
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.list.model.ListAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.Green
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

            if (product.discountPercentage > 0.0) {
                PriceWithDiscount(product = product)
            } else {
                PriceWithoutDiscount(product = product)
            }
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = ExtendedTheme.colors.labelPrimary,
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = "${product.rating}",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = ExtendedTheme.colors.labelPrimary
                    )
                )
            }
        }
    }
}

@Composable
private fun PriceWithDiscount(product: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${product.newPrice}",
            style = MaterialTheme.typography.headlineLarge.copy(color = Green)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = ExtendedTheme.colors.supportSeparator,
                textDecoration = TextDecoration.LineThrough
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "${product.discountPercentage}%",
            style = MaterialTheme.typography.headlineMedium.copy(color = Green)
        )
    }
}

@Composable
private fun PriceWithoutDiscount(product: Product) {
    Text(
        text = "$${product.price}",
        style = MaterialTheme.typography.headlineLarge.copy(
            color = ExtendedTheme.colors.labelSecondary
        )
    )
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
