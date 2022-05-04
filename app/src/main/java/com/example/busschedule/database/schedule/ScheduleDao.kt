package com.example.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    // The first screen shows all the bus stops in ascending order by arrival time.
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>


    // For the second query, you also want to select all columns from the schedule table.
    // However, you only want results that match the selected stop name
    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): Flow<List<Schedule>>

}


/**
 *
 * You can reference Kotlin values from the query by preceding it with a colon (:)
 * (e.g. :stopName from the function parameter).
 *
 * Flow<>
 * To fix this, you can take advantage of a Kotlin feature called asynchronous flow (often just called flow)
 * that will allow the DAO to continuously emit data from the databas
 *
 */