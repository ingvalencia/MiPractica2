package com.example.mipractica2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.mipractica2.model.Manga
import java.lang.Exception

class DbMangas(context: Context?) : DBHelper(context) {

    //CRUD
    val context = context

    fun insertManga(title: String, gender: String, creator: String): Long{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

        try {

            val values = ContentValues()

            values.put("title", title)
            values.put("gender", gender)
            values.put("creator", creator)

            id = db.insert(TABLE_MANGA, null, values)

        }catch (e: Exception){

        }finally {
            db.close()
        }

        return id
    }

    fun getMangas(): ArrayList<Manga>{

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var listMangas = ArrayList<Manga>()
        var mangaTmp: Manga? = null
        var cursorMangas: Cursor? = null

        cursorMangas = db.rawQuery("SELECT * FROM $TABLE_MANGA", null)

        if(cursorMangas.moveToFirst()){
            do {
                mangaTmp = Manga(cursorMangas.getInt(0), cursorMangas.getString(1), cursorMangas.getString(2), cursorMangas.getString(3))
                listMangas.add(mangaTmp)
            }while(cursorMangas.moveToNext())
        }
        cursorMangas.close()

        return listMangas


    }

    fun getManga(id: Int): Manga?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var manga: Manga? = null
        var cursorMangas: Cursor? = null

        cursorMangas = db.rawQuery("SELECT * FROM $TABLE_MANGA WHERE id = $id LIMIT 1", null)

        if (cursorMangas.moveToFirst()){
            manga = Manga(cursorMangas.getInt(0), cursorMangas.getString(1), cursorMangas.getString(2), cursorMangas.getString(3))
        }
        cursorMangas.close()

        return manga


    }

    fun updateManga(id: Int, title: String, gender: String, creator: String): Boolean{

        var BanderaCorrecto = false

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
                db.execSQL("UPDATE $TABLE_MANGA SET title = '$title', gender = '$gender', creator = '$creator' WHERE id = $id")
                BanderaCorrecto = true

        }catch (e: java.lang.Exception){

        }finally {
            db.close()
        }

        return BanderaCorrecto

    }

    fun deleteManga(id: Int): Boolean{

        var BanderaCorrecto = true

        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_MANGA  WHERE id = $id")
            BanderaCorrecto = true

        }catch (e: java.lang.Exception){

        }finally {
            db.close()
        }

        return BanderaCorrecto

    }






}

