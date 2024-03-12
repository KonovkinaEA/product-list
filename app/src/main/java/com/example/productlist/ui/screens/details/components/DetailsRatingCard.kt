package com.example.productlist.ui.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@Composable
fun RatingCard(rating: Double) {
    ItemCard(paddings = 0.dp, backgroundColor = ExtendedTheme.colors.backElevated) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(7.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating icon",
                tint = ExtendedTheme.colors.labelPrimary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "$rating",
                style = MaterialTheme.typography.displaySmall.copy(
                    color = ExtendedTheme.colors.labelPrimary
                )
            )
        }
    }
}

@Preview
@Composable
private fun RatingCardPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        RatingCard(rating = 4.51)
    }
}
