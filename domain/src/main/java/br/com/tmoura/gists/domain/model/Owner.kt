package br.com.tmoura.gists.domain.model

data class Owner(
        val id: Long,
        val login: String,
        val avatarUrl: String
) {
    companion object {
        val ANONYMOUS = Owner(
                id = 0,
                login = "Anonymous",
                avatarUrl = "https://assets-cdn.github.com/images/gravatars/gravatar-user-420.png")
    }
}