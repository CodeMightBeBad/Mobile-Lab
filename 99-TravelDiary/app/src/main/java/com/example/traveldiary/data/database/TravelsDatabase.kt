package com.example.traveldiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Travel::class], version = 1)
abstract class TravelsDatabase : RoomDatabase() {
    abstract fun travelsDAO() : TravelsDAO
}
