package com.weather.weather.data.cloud.model

import com.google.gson.annotations.SerializedName

class Temp {

    @SerializedName("main")
    var main: MainTemp? = null
}