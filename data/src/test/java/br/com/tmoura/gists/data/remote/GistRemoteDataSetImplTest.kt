package br.com.tmoura.gists.data.remote

import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.data.factory.GistPayloadFactory
import br.com.tmoura.gists.data.remote.mapper.toDomain
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GistRemoteDataSetImplTest {

    private lateinit var api: GistApi
    private lateinit var dataSet: GistRemoteDataSet
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

}