package com.example.productlist.ui.screens.list.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.productlist.ui.screens.list.model.ListAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopAppBar(firstPage: Boolean, lastPage: Boolean, onAction: (ListAction) -> Unit) {
    TopAppBar(
        modifier = Modifier.shadow(10.dp),
        title = {},
        navigationIcon = {
            if (!firstPage) {
                IconButton(onClick = { onAction(ListAction.PreviousPage) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Previous page"
                    )
                }
            }
        },
        actions = {
            if (!lastPage) {
                IconButton(onClick = { onAction(ListAction.NextPage) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next page"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ExtendedTheme.colors.backPrimary,
            navigationIconContentColor = ExtendedTheme.colors.labelPrimary,
            actionIconContentColor = ExtendedTheme.colors.labelPrimary
        )
    )
}

@Preview
@Composable
private fun TopAppBarPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        ListTopAppBar(firstPage = false, lastPage = false, onAction = {})
    }
}
