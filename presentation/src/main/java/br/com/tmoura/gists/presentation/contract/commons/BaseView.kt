package br.com.tmoura.gists.presentation.contract.commons

interface BaseView<in T: BasePresenter> {

    fun bindPresenter(presenter: T)

}