package br.com.tmoura.gists.data.factory

import br.com.tmoura.gists.data.remote.model.OwnerPayload
import br.com.tmoura.gists.domain.factory.Factory

class OwnerPayloadFactory: Factory<OwnerPayload>() {

    override fun create() = OwnerPayload(
            id = long(),
            login = uuid(),
            avatarUrl = uuid()
    )

}