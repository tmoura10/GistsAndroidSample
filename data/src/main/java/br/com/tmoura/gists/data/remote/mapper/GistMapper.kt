package br.com.tmoura.gists.data.remote.mapper

import br.com.tmoura.gists.data.remote.model.FilePayload
import br.com.tmoura.gists.data.remote.model.GistPayload
import br.com.tmoura.gists.data.remote.model.OwnerPayload
import br.com.tmoura.gists.domain.model.Owner
import br.com.tmoura.gists.domain.model.Gist
import com.google.gson.Gson

fun GistPayload.toDomain(): Gist {

    val fileEntry = this.files.asJsonObject.entrySet().firstOrNull()
    val file = Gson().fromJson(fileEntry?.value, FilePayload::class.java)

    return Gist(id = this.id,
            title = this.description ?: "-",
            language = file?.language ?: "",
            rawUrl = file?.rawUrl ?: "",
            owner = this.owner?.toDomain() ?: Owner.ANONYMOUS,
            isStarred = false)
}

fun OwnerPayload.toDomain(): Owner {
    return Owner(
            id = this.id,
            login = this.login,
            avatarUrl = this.avatarUrl
    )
}