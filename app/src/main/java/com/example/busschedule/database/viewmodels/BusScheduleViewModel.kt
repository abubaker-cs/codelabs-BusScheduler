package com.example.busschedule.database.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

//  It should take a single parameter of type ScheduleDao
class BusScheduleViewModel(private val scheduleDao: ScheduleDao) : ViewModel() {

    // getAll()
    fun fullSchedule(): List<Schedule> = scheduleDao.getAll()

    // getByStopName()
    fun scheduleForStopName(name: String): List<Schedule> = scheduleDao.getByStopName(name)

}

/**
 * Why to initialize through Factory
 * =================================
 * Instead of directly initializing the ViewModel class, we are going to rely on ModelFactory
 * to handle Memory Management tasks, otherwise our Fragment will have to perform these additional
 * tasks manually.
 *
 * This will ensure that viewModel will be lifecycle aware without your fragment having to handle this directly
 *
 * Factory: This will instantiate view model objects for you.
 *
 */
class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {

    // Returns a BusScheduleViewModelFactory with some error checking
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        //
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T

        }

        // Error Handling
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}