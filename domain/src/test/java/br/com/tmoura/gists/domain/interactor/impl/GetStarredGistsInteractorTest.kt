package br.com.tmoura.gists.domain.interactor.impl

import br.com.tmoura.gists.domain.ReactiveTransformer
import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.factory.GistFactory
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetStarredGistsInteractorTest {

    private lateinit var gistRepository: GistRepository
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var getStarredGistsInteractor: GetStarredGistsInteractorImpl
    private lateinit var transformer: ReactiveTransformer<List<Gist>>
    private val factory = GistFactory()

    @Before
    fun `run before each test`() {
        gistRepository = mock()
        schedulersProvider = mock()
        transformer = mock()
        getStarredGistsInteractor = GetStarredGistsInteractorImpl(gistRepository = gistRepository,
                schedulersProvider = schedulersProvider)

        whenever(schedulersProvider.buildTransformer<List<Gist>>()).thenReturn(transformer)
    }

    @Test
    fun `favorite gists are retrieved with success`() {
        val gists = factory.createList(10)
        val single = Single.just(gists)

        whenever(gistRepository.getStarredGists()).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        val subscriber = getStarredGistsInteractor.execute(Unit).test()

        subscriber.assertNoErrors()
        subscriber.assertValue(gists)
    }

    @Test
    fun `interactor is executed with right transformer`() {
        val gists = factory.createList(10)
        val single = Single.just(gists)

        whenever(gistRepository.getStarredGists()).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        getStarredGistsInteractor.execute(Unit).test()

        verify(transformer).apply(single)
    }

    @Test
    fun `exceptions are handled on Single onError`() {
        val exception = Exception("test exception")
        val single = Single.error<List<Gist>>(exception)

        whenever(gistRepository.getStarredGists()).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        val subscriber = getStarredGistsInteractor.execute(Unit).test()

        subscriber.assertError(exception)
    }

}