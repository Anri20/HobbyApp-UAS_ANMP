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

    @Query("select * from accounts")
    suspend fun getAll(): List<Account>

    @Query("select * from accounts where username=:username and password=:password")
    suspend fun loginAccount(username: String, password: String): List<Account>

    @Query("select * from accounts where username=:username")
    suspend fun compare(username: String): List<Account>

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)
}