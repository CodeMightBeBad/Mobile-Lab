package com.example.traveldiary.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelsDAO {
    @Query("SELECT * FROM travel")
    fun getAll(): Flow<List<Travel>>

    @Upsert
    suspend fun upsert(travel: Travel)

    @Delete
    suspend fun delete(travel: Travel)
}
