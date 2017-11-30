package br.com.tmoura.gists.domain.factory

import br.com.tmoura.gists.domain.model.Owner

class AuthorFactory : Factory<Owner>() {

    override fun create() = Owner(
            id = long(),
            login = uuid(),
            avatarUrl = uuid()
    )

}