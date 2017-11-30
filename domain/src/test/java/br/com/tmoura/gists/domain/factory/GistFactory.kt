package br.com.tmoura.gists.domain.factory

import br.com.tmoura.gists.domain.model.Gist

class GistFactory : Factory<Gist>() {

    private val authorFactory = AuthorFactory()

    override fun create() = Gist(
            id = uuid(),
            title = uuid(),
            language = uuid(),
            rawUrl = uuid(),
            isStarred = boolean(),
            owner = authorFactory.create()
    )

}