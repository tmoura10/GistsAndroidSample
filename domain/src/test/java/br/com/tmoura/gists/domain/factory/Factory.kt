package br.com.tmoura.gists.domain.factory

import java.util.Random
import java.util.UUID

abstract class Factory<out T> {

    abstract fun create() : T

    private val random = Random()

    fun long() = random.nextLong()

    fun uuid() = UUID.randomUUID().toString()

    fun boolean() = random.nextBoolean()

    fun createList(count: Int): List<T> {
        return (1..count).map { create() }
    }

}