package br.com.tmoura.gists.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.startsWith
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SchedulersTransformerTest {

    private lateinit var schedulerToSubscribe: Scheduler
    private lateinit var schedulerToObserve: Scheduler
    private lateinit var schedulersTransformer: SchedulersTransformer<String>

    private val ioSchedulerThreadName = "RxCachedThreadScheduler"
    private val computationSchedulerThreadName = "RxComputationThreadPool"

    @Before
    fun `run before each test`() {
        schedulerToObserve = Schedulers.computation()
        schedulerToSubscribe = Schedulers.io()
        schedulersTransformer = SchedulersTransformer(schedulerToSubscribe = schedulerToSubscribe,
                schedulerToObserve = schedulerToObserve)
    }

    @Test
    fun `apply schedulers to single`() {
        val testSubscriber = Single.create<String> { subscriber ->
            subscriber.onSuccess(Thread.currentThread().name)
        }.compose(schedulersTransformer).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue { v -> v.startsWith(ioSchedulerThreadName) }
        assertThat(testSubscriber.lastThread().name, startsWith(computationSchedulerThreadName))
    }

    @Test
    fun `apply schedulers to completable`() {
        var subscriberThreadName = ""
        val testSubscriber = Completable.create { subscriber ->
            subscriberThreadName = Thread.currentThread().name
            subscriber.onComplete()
        }.compose(schedulersTransformer).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        assertThat(subscriberThreadName, startsWith(ioSchedulerThreadName))
        assertThat(testSubscriber.lastThread().name, startsWith(computationSchedulerThreadName))
    }

    @Test
    fun `apply schedulers to maybe`() {
        val testSubscriber = Maybe.create<String> { subscriber ->
            subscriber.onSuccess(Thread.currentThread().name)
        }.compose(schedulersTransformer).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue { v -> v.startsWith(ioSchedulerThreadName) }
        assertThat(testSubscriber.lastThread().name, startsWith(computationSchedulerThreadName))
    }

    @Test
    fun `apply schedulers to flowable`() {
        val testSubscriber = Flowable.unsafeCreate<String> {  subscriber ->
            subscriber.onNext(Thread.currentThread().name)
            subscriber.onComplete()
        }.compose(schedulersTransformer).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue { v -> v.startsWith(ioSchedulerThreadName) }
        assertThat(testSubscriber.lastThread().name, startsWith(computationSchedulerThreadName))
    }

    @Test
    fun `apply schedulers to observable`() {
        val testSubscriber = Observable.create<String> { subscriber ->
            subscriber.onNext(Thread.currentThread().name)
            subscriber.onComplete()
        }.compose(schedulersTransformer).test()
        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue { v -> v.startsWith(ioSchedulerThreadName) }
        assertThat(testSubscriber.lastThread().name, startsWith(computationSchedulerThreadName))
    }

}