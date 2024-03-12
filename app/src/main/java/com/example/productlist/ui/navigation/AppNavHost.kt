package com.example.productlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.productlist.ui.screens.details.DetailsScreen
import com.example.productlist.ui.screens.list.ListScreen

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = List.route
    ) {
        composable(List.route) {
            ListScreen(navController::navigateToProductDetails)
        }
        composable(Details.routeWithArgs, arguments = Details.arguments) {
            DetailsScreen(onProductClose = {
                navController.navigate(List.route) { popUpTo(List.route) { inclusive = true } }
            })
        }
    }
}

private fun NavController.navigateToProductDetails(id: String = "") {
    this.navigate(Details.navToOrderWithArgs(id))
}
