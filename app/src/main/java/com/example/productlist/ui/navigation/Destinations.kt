package com.example.productlist.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object List : Destination {
    override val route = "list"
}

object Details : Destination {
    const val id = "id"
    override val route = "details"

    const val routeWithArgs = "details/{id}"

    val arguments = listOf(
        navArgument(id) {
            type = NavType.StringType
        }
    )

    fun navToOrderWithArgs(id: String = "") = "$route/$id"
}
