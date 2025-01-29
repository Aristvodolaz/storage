package com.komus.storage

import android.app.Application
import com.komus.storage.utils.SPHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var spHelper: SPHelper
    override fun onCreate() {
        super.onCreate()
    }
}