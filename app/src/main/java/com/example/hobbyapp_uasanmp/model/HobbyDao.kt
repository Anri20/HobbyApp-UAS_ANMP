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
    suspend fun insertAll(account: Account)

    @Query("select * from hobbies")
    fun selectAllHobby(): ArrayList<Hobby>

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)
}