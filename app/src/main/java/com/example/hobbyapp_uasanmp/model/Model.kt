package com.example.hobbyapp_uasanmp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
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
    @ColumnInfo(name = "img_url")
    var imgUrl: String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idaccount")
    var idAccount: Long = 0
}

@Entity(tableName = "hobbies")
data class Hobby(
    @ColumnInfo(name = "hobby_img_url")
    val imgUrl: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "preview")
    val preview: String?,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "account_idaccount")
    val idAccount: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idhobby")
    var idHobby: Long = 0
}

data class AccountWithHobbies(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "idaccount",
        entityColumn = "account_idaccount"
    )
    val hobbies: List<Hobby>
)

data class HobbyAccount(
    @Embedded val hobby: Hobby,
    @Embedded val account: Account
)