package com.example.core.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
	@field:SerializedName("items")
	val items: List<ListUserResponseItem>
)

data class ListUserResponseItem(
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,
)
