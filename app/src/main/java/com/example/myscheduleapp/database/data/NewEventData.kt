package com.example.myscheduleapp.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Using Room
//We create the entity
//Entity == Object
@Entity
data class NewEventData(
    //The primaryKey, it had the option of auto generate
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    //Column's name
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "date")
    var date: String?,
    @ColumnInfo(name = "time")
    var time: String?,
    //This variable is for the visibility
    var visibility: Boolean = false
)