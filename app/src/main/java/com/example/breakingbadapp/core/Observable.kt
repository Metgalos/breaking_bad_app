package com.example.breakingbadapp.core

open class Observable<T> {
    private val observers = mutableListOf<T>()

    fun addObserver(t: T) {
        observers.add(t)
    }

    fun removeObserver(t: T) {
        observers.remove(t)
    }

    fun notify(callback: (T) -> Unit) {
        observers.forEach { callback(it) }
    }
}