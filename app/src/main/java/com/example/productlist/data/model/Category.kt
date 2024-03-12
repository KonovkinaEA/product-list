package com.example.productlist.data.model

enum class Category(val value: String) {
    SMARTPHONES("Smartphones"),
    LAPTOPS("Laptops"),
    FRAGRANCES("Fragrances"),
    SKINCARE("Skincare"),
    GROCERIES("Groceries"),
    HOME_DECORATION("Home-decoration"),
    DEFAULT("Default"),
    ALL("All");

    companion object {

        private val map = entries.associateBy(Category::value)
        fun fromValue(value: String) = map[value] ?: DEFAULT
    }
}
