package com.example.productlist.ui.screens.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun PriceCard(product: Product) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (product.discountPercentage > 0.0) {
            ItemCard(paddings = 10.dp, backgroundColor = Green) {
                CardText("$${product.newPrice}")
            }
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = ExtendedTheme.colors.supportSeparator,
                    textDecoration = TextDecoration.LineThrough
                )
            )
            Text(
                text = "${product.discountPercentage}%",
                style = MaterialTheme.typography.displaySmall.copy(color = Green),
                modifier = Modifier.padding(7.dp)
            )
        } else {
            ItemCard(paddings = 10.dp, backgroundColor = Green) {
                CardText("$${product.price}")
            }
        }
    }
}

@Composable
private fun CardText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall.copy(
            color = ExtendedTheme.colors.labelPrimary
        ),
        modifier = Modifier.padding(7.dp)
    )
}

@Preview
@Composable
private fun PriceCardPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        PriceCard(product = products.first())
    }
}
