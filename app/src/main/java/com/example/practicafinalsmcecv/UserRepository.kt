package com.example.practicafinalsmcecv

import android.content.ContentValues
import android.content.Context

class UserRepository(context: Context) {

    private val dbHelper = DataBaseHelper(context)

    fun insertUser(username: String, email: String, password: String): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Campos vacíos, no permitir el registro
            return false
        }

        if (isUsernameExists(username)) {
            // Nombre de usuario ya existe, no permitir el registro
            return false
        }

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DataBaseHelper.COLUMN_USERNAME, username)
            put(DataBaseHelper.COLUMN_EMAIL, email)
            put(DataBaseHelper.COLUMN_PASSWORD, password)
        }

        val result = db.insert(DataBaseHelper.TABLE_USERS, null, values) != -1L
        db.close()

        return result
    }

    private fun isUsernameExists(username: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DataBaseHelper.TABLE_USERS} WHERE ${DataBaseHelper.COLUMN_USERNAME}=?", arrayOf(username))

        val result = cursor.count > 0
        cursor.close()
        db.close()

        return result
    }


    fun authenticateUser(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DataBaseHelper.TABLE_USERS} WHERE ${DataBaseHelper.COLUMN_USERNAME}=? AND ${DataBaseHelper.COLUMN_PASSWORD}=?", arrayOf(username, password))

        val result = cursor.count > 0
        cursor.close()
        db.close()

        return result
    }
}