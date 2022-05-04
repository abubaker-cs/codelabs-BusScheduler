package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(

    // Primary Key
    @PrimaryKey
    val id: Int,

    // Stop Name
    @NonNull @ColumnInfo(name = "stop_name")
    val stopName: String,

    // Arrival Time
    @NonNull @ColumnInfo(name = "arrival_time")
    val arrivalTime: Int
)

/**
 * @Entity(tableName="schedule") = Use this class to define the database TABLE
 *
 * @NonNull = In SQL, values can be null by default, which we don't want. So, we are using @NonNull
 *              to make sure that we are on the safe side.
 *
 * @ColumnInfo(name = "") = _ name defined in the Database as opposed to CamelCase Kotlin names
 */