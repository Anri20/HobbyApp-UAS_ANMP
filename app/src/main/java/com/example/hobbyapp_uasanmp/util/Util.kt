package com.example.hobbyapp_uasanmp.util

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import com.example.hobbyapp_uasanmp.R
import com.example.hobbyapp_uasanmp.model.AppDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url: String? = null, progressBar: ProgressBar? = null) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
//                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}

// DATABASE BUILDER
val DB_NAME = "app_database"

fun buildDb(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .addMigrations()
        .build()
}
// DATABASE BUILDER