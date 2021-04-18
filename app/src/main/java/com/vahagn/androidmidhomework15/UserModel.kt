package com.vahagn.androidmidhomework15

class UserModel {
    var code: Int? = null

    var meta: String? = null

    var data : UserData? = null

    data class UserData(
        var id: Int,
        var name: String,
        var description: String,
        var image: String,
        var price: String,
        var discount_amount: String,
        var status: Boolean,
        var categories: List<Category>)

    data class Category(
        var id: Int,
        var name: String
    )
}