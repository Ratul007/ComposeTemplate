package com.example.compose.model

data class ProfileModel(
    var age: String,
    var name: String,
    var email: String,
)

data class UserModel(
    var profile: ProfileModel
)