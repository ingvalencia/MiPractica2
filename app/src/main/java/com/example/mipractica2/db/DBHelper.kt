package com.example.mipractica2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME , null ,DATABASE_VERSION ) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_MANGA(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, gender TEXT NOT NULL, creator TEXT NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE $TABLE_MANGA")
        onCreate(db)
    }

    //statics
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "manga.db"
        public  const val TABLE_MANGA = "manga"
    }


}