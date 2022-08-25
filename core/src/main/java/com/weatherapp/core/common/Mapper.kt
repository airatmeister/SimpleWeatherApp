package com.weatherapp.core.common

interface Mapper<I, O> {

    fun map(input: I): O
}