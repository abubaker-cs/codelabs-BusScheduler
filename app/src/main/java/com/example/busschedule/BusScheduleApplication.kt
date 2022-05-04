package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {

    // It will hold the result of getDatabase()
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

}