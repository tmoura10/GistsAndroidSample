package br.com.tmoura.gists.domain

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.MaybeSource
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import org.reactivestreams.Publisher

class SchedulersTransformer<T> (
        val schedulerToSubscribe: Scheduler,
        val schedulerToObserve: Scheduler
) : ReactiveTransformer<T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(schedulerToSubscribe).observeOn(schedulerToObserve)
    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(schedulerToSubscribe).observeOn(schedulerToObserve)
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(schedulerToSubscribe).observeOn(schedulerToObserve)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(schedulerToSubscribe).observeOn(schedulerToObserve)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(schedulerToSubscribe).observeOn(schedulerToObserve)
    }
}