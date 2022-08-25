package com.weatherapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.launchActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun test_MainActivity_check_package_name(){
        launchActivity<MainActivity>().use { scenario ->
            scenario.onActivity {
                Assert.assertEquals(
                    "com.weatherapp",
                    it.applicationContext.packageName
                )
            }
        }
    }
}