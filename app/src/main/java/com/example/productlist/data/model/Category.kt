package com.example.productlist.data.model

enum class Category(val value: String) {
    SMARTPHONES("smartphones"),
    LAPTOPS("laptops"),
    FRAGRANCES("fragrances"),
    SKINCARE("skincare"),
    GROCERIES("groceries"),
    HOME_DECORATION("home-decoration"),
    DEFAULT("default");

    companion object {

        private val map = entries.associateBy(Category::value)
        fun fromValue(value: String) = map[value] ?: DEFAULT
    }
}
