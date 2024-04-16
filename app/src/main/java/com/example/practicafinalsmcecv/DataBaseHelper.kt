package com.example.practicafinalsmcecv

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.security.MessageDigest

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "desized.db"

        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"

        const val TABLE_PARTIDOS = "partidos"
        const val COLUMN_PARTIDO_ID = "id"
        const val COLUMN_PARTIDO_TITULO = "titulo"
        const val COLUMN_PARTIDO_FECHA = "fecha"
        const val COLUMN_PARTIDO_HORA = "hora"
        const val COLUMN_PARTIDO_LUGAR = "lugar"
        const val COLUMN_PARTIDO_DESCRIPCION = "descripcion"
    }

    private val SQL_CREATE_USERS =
        "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_USERNAME TEXT UNIQUE, $COLUMN_EMAIL TEXT UNIQUE, $COLUMN_PASSWORD TEXT)"

    private val SQL_CREATE_PARTIDOS =
        "CREATE TABLE $TABLE_PARTIDOS ($COLUMN_PARTIDO_ID INTEGER PRIMARY KEY, $COLUMN_PARTIDO_TITULO TEXT, $COLUMN_PARTIDO_FECHA TEXT, $COLUMN_PARTIDO_HORA TEXT, $COLUMN_PARTIDO_LUGAR TEXT, $COLUMN_PARTIDO_DESCRIPCION TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_USERS)
        db.execSQL(SQL_CREATE_PARTIDOS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PARTIDOS")
        onCreate(db)
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}