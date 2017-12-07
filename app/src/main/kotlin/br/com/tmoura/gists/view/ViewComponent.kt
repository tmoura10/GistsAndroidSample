package br.com.tmoura.gists.view

import android.view.ViewGroup

interface ViewComponent {
    fun attach(root: ViewGroup)
    fun hide()
    fun show()
}