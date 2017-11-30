package br.com.tmoura.gists.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilePayload(
        @SerializedName("raw_url") val rawUrl: String,
        val language: String
)