package com.example.productlist.ui.screens.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.productlist.data.model.Product
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@Composable
fun ProductItem(product: Product) {
    Text(text = "$product")
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        Box {
            ProductItem(Product(id = 0))
        }
    }
}
