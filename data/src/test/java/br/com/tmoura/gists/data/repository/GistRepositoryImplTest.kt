package br.com.tmoura.gists.data.repository

import br.com.tmoura.gists.data.dataset.GistLocalDataSet
import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.domain.factory.GistFactory
import br.com.tmoura.gists.domain.repository.GistRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GistRepositoryImplTest {

    private lateinit var localDataSet: GistLocalDataSet
    private lateinit var remoteDataSet: GistRemoteDataSet
    private lateinit var repository: GistRepository
    private val factory = GistFactory()

    @Before
    fun `run before each test`() {
        localDataSet = mock()
        remoteDataSet = mock()
        repository = GistRepositoryImpl(
                localDataSet = localDataSet,
                remoteDataSet = remoteDataSet
        )
    }

    @Test
    fun `retrieve gists`() {
        val gists = factory.createList(5)
        val single = Single.just(gists)

        whenever(remoteDataSet.getGists(any())).thenReturn(single)

        val subscriber = repository.getGists(5).test()

        subscriber.assertNoErrors()
        subscriber.assertValue(gists)
    }

}