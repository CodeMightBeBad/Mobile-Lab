package com.example.traveldiary.data.repositories

import com.example.traveldiary.data.database.Trip
import com.example.traveldiary.data.database.TripsDAO

class TripsRepository(private val tripsDAO: TripsDAO) {
    val trips = tripsDAO.getAll()

    suspend fun upsert(trip: Trip) = tripsDAO.upsert(trip)
    suspend fun delete(trip: Trip) = tripsDAO.delete(trip)
}