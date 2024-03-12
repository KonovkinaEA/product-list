package com.example.productlist.ui.screens.list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.productlist.data.model.Product
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.Green
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview
import com.example.productlist.ui.util.products

@Composable
fun Price(product: Product) {
    if (product.discountPercentage > 0.0) {
        PriceWithDiscount(product = product)
    } else {
        PriceWithoutDiscount(product = product)
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

@Preview
@Composable
private fun PriceWithDiscountPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        Price(product = products.first())
    }
}

@Preview
@Composable
private fun PriceWithoutDiscountPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        Price(product = products.first().copy(discountPercentage = 0.0))
    }
}
