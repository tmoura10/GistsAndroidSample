package br.com.tmoura.gists.domain.factory

import br.com.tmoura.gists.domain.model.Author

class AuthorFactory : Factory<Author>() {

    override fun create() = Author(
            id = uuid(),
            name = uuid(),
            avatarUrl = uuid()
    )

}