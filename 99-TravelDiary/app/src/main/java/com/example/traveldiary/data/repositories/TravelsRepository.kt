package com.example.traveldiary.data.repositories

import com.example.traveldiary.data.database.Travel
import com.example.traveldiary.data.database.TravelsDAO

class TravelsRepository(private val travelsDAO: TravelsDAO) {
    val travels = travelsDAO.getAll()

    suspend fun upsert(travel: Travel) = travelsDAO.upsert(travel)
    suspend fun delete(travel: Travel) = travelsDAO.upsert(travel)
}