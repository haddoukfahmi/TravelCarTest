package com.travelcar_test

import android.app.Application

class Application : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: Application
    }

}