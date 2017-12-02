package br.com.tmoura.gists.presentation.model

data class GistItemViewModel(
        val id: String,
        val title: String,
        val language: String,
        val ownerName: String,
        val ownerAvatarUrl: String
)