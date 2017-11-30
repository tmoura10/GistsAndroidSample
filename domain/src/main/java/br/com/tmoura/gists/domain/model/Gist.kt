package br.com.tmoura.gists.domain.model

data class Gist(
        val id: String,
        val title: String,
        val language: String,
        val rawUrl: String,
        val owner: Owner,
        var isStarred: Boolean
)