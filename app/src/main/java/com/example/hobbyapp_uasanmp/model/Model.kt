package com.example.hobbyapp_uasanmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "accounts")
data class Account(
    @ColumnInfo(name = "nama_depan")
    var namaDepan: String?,
    @ColumnInfo(name = "nama_belakang")
    var namaBelakang: String?,
    @ColumnInfo(name = "username")
    var username: String?,
    @ColumnInfo(name = "password")
    var password: String?,
    @ColumnInfo(name = "imgUrl")
    var imgUrl: String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_account")
    var idAccount: Int = 0
}

@Entity(tableName = "hobbies")
data class Hobby(
    @ColumnInfo(name = "hobby_img_url")
    val imgUrl: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "writer")
    val writer: String?,
    @ColumnInfo(name = "preview")
    val preview: String?,
    @ColumnInfo(name = "content")
    val content: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_hobby")
    var idHobby: Int = 0
}