package com.example.myscheduleapp.database.dao

import androidx.room.*
import com.example.myscheduleapp.database.data.NewEventData

//Se encarga de las consultas
@Dao
interface NewEventDao {

    //Todos
    @Query("SELECT * FROM NewEventData")
    fun getAll(): List<NewEventData>

    //Inserta
    @Insert
    fun insert(newEventData: ArrayList<NewEventData>)

    //Actualiza
    @Update
    fun update(newEventData: NewEventData)

    //Borra
    @Delete
    fun delete(newEventData: NewEventData)
}