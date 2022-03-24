package com.example.githubuser.data.remote.githubresponse

import com.google.gson.annotations.SerializedName

data class ListResponse(

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class ItemsItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,
)
