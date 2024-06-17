package com.example.hobbyapp_uasanmp.util

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hobbyapp_uasanmp.R
import com.example.hobbyapp_uasanmp.model.AppDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


fun ImageView.loadImage(url: String? = null, progressBar: ProgressBar? = null) {
//    if url is empty, example = ""
    if (url!!.isEmpty()) {
        findViewById<ImageView>(R.id.imgAddHobby1).setImageResource(R.drawable.baseline_error_24)
    } else {
        Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.baseline_error_24)
            .into(this, object : Callback {
                @Override
                override fun onSuccess() {
//                progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    Toast.makeText(context, "Error loading ImageURL", Toast.LENGTH_SHORT).show()
                }
            })
    }
}

fun isIndexValid(list: ArrayList<String>, index: Int): Boolean {
    return index in 0 until list.size
}

// DATABASE BUILDER
val DB_NAME = "app_database"

fun buildDb(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_3_4)
        .build()
}
// DATABASE BUILDER


// MIGRATIONS

// MIGRATION_1_2 means this migration is used for upgrading the db from version 1 to 2
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE hobbies ADD COLUMN account_idaccount BIGINT DEFAULT 0 NOT NULL;"
        )
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE hobbies_new (
                idhobby INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                hobby_img_url TEXT,
                title TEXT,
                preview TEXT,
                content TEXT NOT NULL,
                account_idaccount INTEGER NOT NULL
            )
        """.trimIndent()
        )

        db.execSQL(
            """
            INSERT INTO hobbies_new (hobby_img_url, title, preview, content, account_idaccount, idhobby)
            SELECT hobby_img_url, title, preview, content, account_idaccount, idhobby
            FROM hobbies
        """.trimIndent()
        )

        db.execSQL("DROP TABLE hobbies")

        db.execSQL("ALTER TABLE hobbies_new RENAME TO hobbies")
    }
}

// MIGRATIONS
