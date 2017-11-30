package br.com.tmoura.gists.data.factory

import br.com.tmoura.gists.data.remote.model.GistPayload
import br.com.tmoura.gists.domain.factory.Factory
import com.google.gson.Gson
import com.google.gson.JsonObject

class GistPayloadFactory : Factory<GistPayload>() {

    private val ownerFactory = OwnerPayloadFactory()
    private val fileFactory = FilePayloadFactory()
    private val gson = Gson()

    override fun create(): GistPayload {
        val fileJsonObject = JsonObject()
        fileJsonObject.add(uuid(), gson.toJsonTree(fileFactory.create()))
        return GistPayload(
                id = uuid(),
                description = uuid(),
                owner = ownerFactory.create(),
                files = fileJsonObject
        )
    }
}