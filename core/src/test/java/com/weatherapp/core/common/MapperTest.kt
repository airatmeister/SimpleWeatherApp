package com.weatherapp.core.common

import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test
    fun test_map(){
        val mapper = TestMapper()
        Assert.assertEquals(mapper.map("a"), "a mapped")
    }

    @Test
    fun test_call_map_true(){
        val mapper = Mock()
        val testCallMapTrue = TestCallMapTrue(mapper)
        testCallMapTrue.main()
        Assert.assertEquals(mapper.isCalled, "called")
    }

    @Test
    fun test_call_map_false(){
        val mapper = Mock()
        val testCallMapFalse = TestCallMapFalse(mapper)
        testCallMapFalse.main()
        Assert.assertEquals(mapper.isCalled, "not called")
    }

    class TestMapper: Mapper<String, String>{

        override fun map(input: String) = "$input mapped"
    }

    class Mock: Mapper<String, String> {

        var isCalled = "not called"

        override fun map(input: String): String {
            isCalled = "called"
            return ""
        }
    }

    class TestCallMapTrue(private val mapper: Mapper<String, String>) {

        fun main() = mapper.map("")
    }

    class TestCallMapFalse(private val mapper: Mapper<String, String>) {

        fun main() = Unit
    }
}