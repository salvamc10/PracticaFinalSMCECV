package com.example.practicafinalsmcecv

import android.content.ContentValues
import android.content.Context

class UserRepository(context: Context) {

    private val dbHelper = DataBaseHelper(context)
    fun insertUser(username: String, email: String, password: String): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) return false

        val hashedPassword = dbHelper.hashPassword(password)

        return dbHelper.writableDatabase.use { db ->
            val values = ContentValues().apply {
                put(DataBaseHelper.COLUMN_USERNAME, username)
                put(DataBaseHelper.COLUMN_EMAIL, email)
                put(DataBaseHelper.COLUMN_PASSWORD, hashedPassword)
            }
            db.insert(DataBaseHelper.TABLE_USERS, null, values) != -1L
        }
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
        val hashedPassword = dbHelper.hashPassword(password)

        dbHelper.readableDatabase.use { db ->
            val cursor = db.rawQuery(
                "SELECT * FROM ${DataBaseHelper.TABLE_USERS} WHERE ${DataBaseHelper.COLUMN_USERNAME}=? AND ${DataBaseHelper.COLUMN_PASSWORD}=?",
                arrayOf(username, hashedPassword)
            )

            val userExists = cursor.count > 0
            cursor.close()
            return userExists
        }
    }
}