package br.com.tmoura.gists.domain.model

data class Gist(
        val id: String,
        val title: String,
        val language: String,
        val rawUrl: String,
        val author: Author,
        var isStarred: Boolean
)