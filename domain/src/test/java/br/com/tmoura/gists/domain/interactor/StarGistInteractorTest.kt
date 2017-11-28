package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.ReactiveTransformer
import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.factory.GistFactory
import br.com.tmoura.gists.domain.repository.GistRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

class StarGistInteractorTest {

    private lateinit var gistRepository: GistRepository
    private lateinit var schedulersProvider: SchedulersProvider
    private lateinit var transformer: ReactiveTransformer<Any>
    private lateinit var starGistInteractor: StarGistInteractor
    private val factory = GistFactory()

    @Before
    fun `run before each test`() {
        gistRepository = mock()
        schedulersProvider = mock()
        transformer = mock()
        starGistInteractor = StarGistInteractor(gistRepository = gistRepository,
                schedulersProvider = schedulersProvider)

        whenever(schedulersProvider.buildTransformer<Any>()).thenReturn(transformer)
    }

    @Test
    fun `star a gist and returns a completable`() {
        val gist = factory.create()
        val completable = Completable.complete()

        whenever(gistRepository.starGist(gist)).thenReturn(completable)
        whenever(transformer.apply(completable)).thenReturn(completable)

        val subscriber = starGistInteractor.execute(StarGistInteractor.Params(gist = gist)).test()

        subscriber.assertComplete()
    }

    @Test
    fun `interactor is executed with right transformer`() {
        val gist = factory.create()
        val completable = Completable.complete()

        whenever(gistRepository.starGist(gist)).thenReturn(completable)
        whenever(transformer.apply(completable)).thenReturn(completable)

        starGistInteractor.execute(StarGistInteractor.Params(gist = gist)).test()

        verify(transformer).apply(completable)
    }

    @Test
    fun `exceptions are handled on Completable onError`() {
        val exception = Exception("test exception")
        val gist = factory.create()
        val completable = Completable.error(exception)

        whenever(gistRepository.starGist(gist)).thenReturn(completable)
        whenever(transformer.apply(completable)).thenReturn(completable)

        val subscriber = starGistInteractor.execute(StarGistInteractor.Params(gist = gist))
                .test()

        subscriber.assertError(exception)
    }

}