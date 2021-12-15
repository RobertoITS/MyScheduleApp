package com.example.myscheduleapp.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Usando Room
//Creamos la entidad
@Entity
data class NewEventData(
    //Id seria la primaryKey, que se auto genere
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    //Nombre de las columnas
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "date")
    var date: String?,
    @ColumnInfo(name = "time")
    var time: String?,
    var visibility: Boolean = false
)