package br.com.tmoura.gists.domain

import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Scheduler
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SchedulersProviderImplTest {

    private lateinit var schedulerToSubscribe: Scheduler
    private lateinit var schedulerToObserve: Scheduler
    private lateinit var schedulerProvider: SchedulersProvider


    @Before
    fun `run before each test`() {
        schedulerToObserve = mock()
        schedulerToSubscribe = mock()
        schedulerProvider = SchedulersProviderImpl(
                schedulerToSubscribe = schedulerToSubscribe,
                schedulerToObserve = schedulerToObserve
        )
    }

    @Test
    fun `transformer is created`() {
        val transformer = schedulerProvider.buildTransformer<Any>()
        assertThat(transformer, instanceOf(SchedulersTransformer::class.java))
        val schedulersTransformer = transformer as SchedulersTransformer<Any>
        assertEquals(schedulersTransformer.schedulerToObserve, schedulerToObserve)
        assertEquals(schedulersTransformer.schedulerToSubscribe, schedulerToSubscribe)
    }

}