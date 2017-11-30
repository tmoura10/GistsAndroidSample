package br.com.tmoura.gists.data.remote.mapper

import br.com.tmoura.gists.data.factory.GistPayloadFactory
import br.com.tmoura.gists.data.remote.model.FilePayload
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class GistMapperTest {

    private val factory = GistPayloadFactory()
    private val gson = Gson()

    @Test
    fun `payload is mapped to domain`() {
        val payload = factory.create()
        val filePayload = gson.fromJson(payload.files.asJsonObject.entrySet().first().value,
                FilePayload::class.java)
        val mapped = payload.toDomain()

        assertEquals(payload.id, mapped.id)
        assertEquals(payload.description, mapped.title)
        assertEquals(payload.owner?.id, mapped.owner.id)
        assertEquals(payload.owner?.avatarUrl, mapped.owner.avatarUrl)
        assertEquals(payload.owner?.login, mapped.owner.login)
        assertEquals(filePayload.language, mapped.language)
        assertEquals(filePayload.rawUrl, mapped.rawUrl)
        assertFalse(mapped.isStarred)
    }

}