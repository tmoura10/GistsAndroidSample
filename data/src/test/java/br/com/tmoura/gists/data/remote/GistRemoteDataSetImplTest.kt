package br.com.tmoura.gists.data.remote

import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.data.factory.GistPayloadFactory
import br.com.tmoura.gists.data.remote.mapper.toDomain
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GistRemoteDataSetImplTest {

    private lateinit var api: GistApi
    private lateinit var dataSet: GistRemoteDataSetImpl
    private val factory = GistPayloadFactory()

    @Before
    fun `run before each test`() {
        api = mock()
        dataSet = GistRemoteDataSetImpl(api)
    }

    @Test
    fun `retrieve gists`() {
        val payload = factory.createList(5)
        val single = Single.just(payload)
        val gists = payload.map { it.toDomain() }

        whenever(api.getGists(any(), any())).thenReturn(single)

        val subscriber = dataSet.getGists(5).test()
        subscriber.assertValue(gists)
    }

    @Test
    fun `determine correct page number given items count`() {
        assertEquals(2, dataSet.page(3, 2))
        assertEquals(1, dataSet.page(0, 10))
        assertEquals(1, dataSet.page(6, 10))
        assertEquals(2, dataSet.page(10, 10))
        assertEquals(27, dataSet.page(2698, 100))
    }

    @Test
    fun `retrieve gist`() {
        val payload = factory.create()
        val single = Single.just(payload)
        val gist = payload.toDomain()

        whenever(api.getGist(any())).thenReturn(single)

        val subscriber = dataSet.getGist("abcd").test()
        subscriber.assertValue(gist)
    }

}