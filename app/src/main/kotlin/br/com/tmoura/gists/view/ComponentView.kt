package br.com.tmoura.gists.view

import android.view.ViewGroup

interface ComponentView {
    fun attach(root: ViewGroup)
}