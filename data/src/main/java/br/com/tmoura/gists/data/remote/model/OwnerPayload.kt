package br.com.tmoura.gists.data.remote.model

import com.google.gson.annotations.SerializedName

data class OwnerPayload(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)