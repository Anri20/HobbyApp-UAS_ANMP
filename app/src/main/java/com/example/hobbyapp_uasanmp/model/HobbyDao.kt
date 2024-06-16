package com.example.hobbyapp_uasanmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HobbyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hobby: Hobby)

    @Query("select * from hobbies")
    suspend fun getAll(): List<Hobby>

    @Update
    suspend fun update(hobby: Hobby)

    @Delete
    suspend fun delete(hobby: Hobby)
}