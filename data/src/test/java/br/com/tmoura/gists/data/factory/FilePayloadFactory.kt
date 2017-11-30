package br.com.tmoura.gists.data.factory

import br.com.tmoura.gists.data.remote.model.FilePayload
import br.com.tmoura.gists.domain.factory.Factory

class FilePayloadFactory : Factory<FilePayload>() {

    override fun create() = FilePayload(
            rawUrl = uuid(),
            language = uuid()
    )

}