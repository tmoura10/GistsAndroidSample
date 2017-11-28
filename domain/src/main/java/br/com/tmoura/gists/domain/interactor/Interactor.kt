package br.com.tmoura.gists.domain.interactor

interface Interactor<in P, out R> {
    fun execute(params: P): R
}