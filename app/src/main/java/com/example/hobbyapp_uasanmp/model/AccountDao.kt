package com.example.hobbyapp_uasanmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.net.PasswordAuthentication

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Account)

    @Query("select * from accounts where username=:username and password=:password")
    fun selectAccount(username: String, password: String)

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)
}