package br.com.tmoura.gists.domain

import io.reactivex.Scheduler
import javax.inject.Inject

class SchedulersProviderImpl(
        private val schedulerToSubscribe: Scheduler,
        private val schedulerToObserve: Scheduler
): SchedulersProvider {

    override fun <T> buildTransformer(): ReactiveTransformer<T> {
        return SchedulersTransformer(schedulerToSubscribe = schedulerToSubscribe,
                schedulerToObserve = schedulerToObserve)
    }

}