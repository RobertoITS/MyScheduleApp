package com.example.myscheduleapp.database.dao

import androidx.room.*
import com.example.myscheduleapp.database.data.NewEventData

//Handles queries
@Dao
interface NewEventDao {

    //All data from database
    @Query("SELECT * FROM NewEventData")
    fun getAllUnDoneEvents(): List<NewEventData>

    //Insert new entry
    @Insert
    fun insert(newEventData: ArrayList<NewEventData>)

    //Update a entry
    @Update
    fun update(newEventData: NewEventData)

    //Delete a entry
    @Delete
    fun delete(newEventData: NewEventData)
}