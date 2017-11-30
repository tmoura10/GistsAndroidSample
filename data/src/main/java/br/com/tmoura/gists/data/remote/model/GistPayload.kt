package br.com.tmoura.gists.data.remote.model

import com.google.gson.JsonElement

data class GistPayload(
        val id: String,
        val description: String,
        val owner: OwnerPayload?,
        val files: JsonElement
)