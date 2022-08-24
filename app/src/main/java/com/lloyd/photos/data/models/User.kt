package com.lloyd.photos.data.models

data class User(
    val accepted_tos: Boolean?,
    val bio: String?,
    val first_name: String?,
    val for_hire: Boolean?,
    val id: String?,
    val instagram_username: String?,
    val last_name: String?,
    val location: String?,
    val name: String?,
    val portfolio_url: String?,
    val profile_image: ProfileImage?,
)