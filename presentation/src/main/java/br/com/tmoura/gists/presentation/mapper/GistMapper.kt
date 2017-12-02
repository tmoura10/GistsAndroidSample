package br.com.tmoura.gists.presentation.mapper

import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.presentation.model.GistItemViewModel

fun Gist.toViewModel(): GistItemViewModel {
    return GistItemViewModel(
            id = this.id,
            title = this.title,
            language = this.language,
            ownerName = this.owner.login,
            ownerAvatarUrl = this.owner.avatarUrl
    )
}