package br.com.tmoura.gists.domain


interface SchedulersProvider {
    fun <T> buildTransformer(): ReactiveTransformer<T>
}