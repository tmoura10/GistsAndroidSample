package br.com.tmoura.gists.domain.factory

import java.util.Random
import java.util.UUID

abstract class Factory<out T> {

    abstract fun create() : T

    private val random = Random()

    fun uuid() = UUID.randomUUID().toString()

    fun boolean() = random.nextBoolean()

}