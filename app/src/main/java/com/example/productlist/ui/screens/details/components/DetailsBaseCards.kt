package com.example.productlist.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.Green
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@Composable
fun BackgroundCard(content: @Composable (BoxScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ExtendedTheme.colors.backSecondary),
        content = content
    )
}

@Composable
fun ItemCard(backgroundColor: Color, content: @Composable (BoxScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(backgroundColor),
        content = content
    )
}

@Preview
@Composable
private fun BackgroundCardPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        BackgroundCard { Text(text = "text1", modifier = Modifier.padding(5.dp)) }
    }
}

@Preview
@Composable
private fun ItemCardPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ItemCard(backgroundColor = Green) {
            Text(
                text = "text2",
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}
