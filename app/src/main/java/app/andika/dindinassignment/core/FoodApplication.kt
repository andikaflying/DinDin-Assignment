package app.andika.dindinassignment.core

import android.app.Application
import android.content.Context
import app.andika.dindinassignment.repository.MainRepository

class FoodApplication : Application() {
    val mainRepository by lazy {
        MainRepository()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: FoodApplication

        fun getContext(): Context? {
            return instance.applicationContext
        }
    }
}