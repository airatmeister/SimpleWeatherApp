package com.weatherapp.core.common

interface Mapper<I, O> {

    fun map(input: I): O

    interface DomainToUi<I, O>: Mapper<I, O>
}