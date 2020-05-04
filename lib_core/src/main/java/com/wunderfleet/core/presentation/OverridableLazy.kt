package com.wunderfleet.core.presentation

class OverridableLazy<T>(var implementation: Lazy<T>): Lazy<T> {
    override val value
        get() = implementation.value
    override fun isInitialized() = implementation.isInitialized()
}