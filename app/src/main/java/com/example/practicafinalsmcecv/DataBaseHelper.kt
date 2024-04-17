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
        "CREATE TABLE $TABLE_PARTIDOS ($COLUMN_PARTIDO_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_PARTIDO_TITULO TEXT, $COLUMN_PARTIDO_FECHA TEXT, $COLUMN_PARTIDO_HORA TEXT, $COLUMN_PARTIDO_LUGAR TEXT, $COLUMN_PARTIDO_DESCRIPCION TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_PARTIDOS)
        db.execSQL(SQL_CREATE_USERS)
        insertInitialPartidos(db)
        insertInitialUser(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PARTIDOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    private fun insertInitialUser(db: SQLiteDatabase){
        insertUser(db, "admin", "admin", "admin@gmail.com")
        insertUser(db, "a", "a", "a@gmail.com")
    }

    private fun insertUser(db: SQLiteDatabase, username: String, password: String, email: String) {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, hashPassword(password))
            put(COLUMN_EMAIL, email)
        }

        db.insert(TABLE_USERS, null, values)
    }

    private fun insertInitialPartidos(db: SQLiteDatabase) {
        insertPartido(db, "UCAM CF - Marbella FC", "2024-01-14", "17:00", "Estadio de La Condomina ", "Segunda RFEF - Grupo 4 - Jornada 18")
        insertPartido(db, "Real Murcia - Atlético Baleares", "2024-01-21", "12:00", "Estadio Enrique Roca", "Primera RFEF - Grupo 2 - Jornada 20")
        insertPartido(db, "Cartagena - Villarreal B", "2024-01-14", "16:15", "Estadio Municipal Cartagonova", "LaLiga Hypermotion - Jornada 22")
        insertPartido(db, "Elche - Real Valladolid", "2024-01-21", "21:00", "Estadio Manuel Martínez Valero", "LaLiga Hypermotion - Jornada 23")
    }

    private fun insertPartido(db: SQLiteDatabase, titulo: String, fecha: String, hora: String, lugar: String, descripcion: String) {
        val values = ContentValues().apply {
            put(COLUMN_PARTIDO_TITULO, titulo)
            put(COLUMN_PARTIDO_FECHA, fecha)
            put(COLUMN_PARTIDO_HORA, hora)
            put(COLUMN_PARTIDO_LUGAR, lugar)
            put(COLUMN_PARTIDO_DESCRIPCION, descripcion)
        }
        db.insert(TABLE_PARTIDOS, null, values)
    }

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}