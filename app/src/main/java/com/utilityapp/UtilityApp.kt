package com.utilityapp

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.utilityapp.data.local.AppDatabase
import com.utilityapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UtilityApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        
        // Initialize Room Database
        AppDatabase.init(this)
        
        // Initialize Koin DI
        startKoin {
            androidContext(this@UtilityApp)
            modules(AppModule.module)
        }
    }
}