package com.example.productlist.ui.screens.details.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil.compose.AsyncImage
import com.example.productlist.R
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(
        state = pagerState,
        key = { images[it] },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) { index ->
        AsyncImage(
            model = images[index],
            contentDescription = "Product image",
            placeholder = painterResource(R.drawable.image_icon),
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun ImageCarouselPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ImageCarousel(listOf(""))
    }
}
