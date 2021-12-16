package com.example.myscheduleapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myscheduleapp.database.dao.NewEventDao
import com.example.myscheduleapp.database.data.NewEventData

//It's in charge of managing the DB
@Database(entities = [NewEventData::class]
    , version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun newEventDao(): NewEventDao
}