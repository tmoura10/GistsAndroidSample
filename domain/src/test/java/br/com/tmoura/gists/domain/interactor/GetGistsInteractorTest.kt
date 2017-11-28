package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.ReactiveTransformer
import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.factory.GistFactory
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetGistsInteractorTest {

    private lateinit var gistRepository: GistRepository
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var getGistsInteractor: GetGistsInteractor
    private lateinit var transformer: ReactiveTransformer<List<Gist>>
    private val factory = GistFactory()

    @Before
    fun `run before each test`() {
        gistRepository = mock()
        schedulersProvider = mock()
        transformer = mock()
        getGistsInteractor = GetGistsInteractor(gistRepository = gistRepository,
                schedulersProvider = schedulersProvider)

        whenever(schedulersProvider.buildTransformer<List<Gist>>()).thenReturn(transformer)
    }

    @Test
    fun `gists are retrieved with success`() {
        val gists = createTestGists()
        val single = Single.just(gists)

        whenever(gistRepository.getGists(any())).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        val subscriber = getGistsInteractor.execute(GetGistsInteractor.Params(loadedItemsCount = 5))
                .test()

        subscriber.assertNoErrors()
        subscriber.assertValue(gists)
    }

    @Test
    fun `interactor is executed with right transformer`() {
        val gists = createTestGists()
        val single = Single.just(gists)

        whenever(gistRepository.getGists(any())).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        getGistsInteractor.execute(GetGistsInteractor.Params(loadedItemsCount = 5))
                .test()

        verify(transformer).apply(single)
    }

    @Test
    fun `exceptions are handled on Single onError`() {
        val exception = Exception("test exception")
        val single = Single.error<List<Gist>>(exception)

        whenever(gistRepository.getGists(any())).thenReturn(single)
        whenever(transformer.apply(single)).thenReturn(single)

        val subscriber = getGistsInteractor.execute(GetGistsInteractor.Params(loadedItemsCount = 5))
                .test()

        subscriber.assertError(exception)
    }

    private fun createTestGists() = listOf(
            factory.create(),
            factory.create(),
            factory.create(),
            factory.create()
    )

}