package com.example.productlist.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.data.model.Product
import com.example.productlist.ui.screens.details.components.ImageCarousel
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.Green
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
            ImageCarousel(images = state.images)
            Spacer(modifier = Modifier.height(15.dp))

            BackgroundCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (state.discountPercentage > 0.0) {
                        BaseCard(backgroundColor = Green) {
                            CardText("${state.newPrice}")
                        }
                        Text(
                            text = "$${state.price}",
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = ExtendedTheme.colors.supportSeparator,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                        Text(
                            text = "${state.discountPercentage}%",
                            style = MaterialTheme.typography.displaySmall.copy(color = Green),
                            modifier = Modifier.padding(7.dp)
                        )
                    } else {
                        BaseCard(backgroundColor = Green) {
                            CardText("${state.price}")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            BackgroundCard {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = state.title,
                            style = MaterialTheme.typography.displayLarge.copy(
                                color = ExtendedTheme.colors.labelPrimary
                            )
                        )
                        RatingCard(state.rating)
                    }
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
}

@Composable
private fun RatingCard(rating: Double) {
    BaseCard(backgroundColor = ExtendedTheme.colors.backElevated) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(7.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
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

@Composable
private fun BackgroundCard(content: @Composable (BoxScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ExtendedTheme.colors.backSecondary),
        content = content
    )
}

@Composable
private fun BaseCard(backgroundColor: Color, content: @Composable (BoxScope.() -> Unit)) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(backgroundColor),
        content = content
    )
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
