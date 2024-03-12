package com.example.productlist.ui.screens.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.productlist.data.model.Category
import com.example.productlist.ui.screens.list.model.ListAction
import com.example.productlist.ui.theme.ExtendedTheme
import com.example.productlist.ui.theme.ProductListTheme
import com.example.productlist.ui.theme.ThemeModePreview

@Composable
fun ListBottomAppBar(onAction: (ListAction) -> Unit) {
    BottomAppBar(
        actions = {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = ExtendedTheme.colors.labelPrimary
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            FilterDropdownMenu(onAction)
        },
        containerColor = ExtendedTheme.colors.backPrimary,
        contentColor = ExtendedTheme.colors.labelPrimary
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterDropdownMenu(onAction: (ListAction) -> Unit) {
    var filterExpanded by remember { mutableStateOf(false) }
    var filter by remember { mutableStateOf(Category.ALL.value) }

    ExposedDropdownMenuBox(
        expanded = filterExpanded,
        onExpandedChange = { filterExpanded = it }
    ) {
        OutlinedTextField(
            value = filter,
            onValueChange = {},
            modifier = Modifier
                .background(color = ExtendedTheme.colors.backSecondary)
                .menuAnchor(),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = filterExpanded)
            },
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = ExtendedTheme.colors.labelPrimary,
                fontWeight = FontWeight.Normal
            )
        )

        ExposedDropdownMenu(
            expanded = filterExpanded,
            onDismissRequest = { filterExpanded = false },
            modifier = Modifier.background(color = ExtendedTheme.colors.backSecondary)
        ) {
            Category.entries.filter { it != Category.DEFAULT }.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.value,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = ExtendedTheme.colors.labelPrimary,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    },
                    onClick = {
                        filter = it.value
                        filterExpanded = false
                        onAction(ListAction.ReloadData(it))
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun TopAppBarPreview(
    @PreviewParameter(ThemeModePreview::class) darkTheme: Boolean
) {
    ProductListTheme(darkTheme = darkTheme) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ListBottomAppBar(onAction = {})
        }
    }
}
