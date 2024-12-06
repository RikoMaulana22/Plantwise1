package com.example.planwise1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.planwise1.domain.Users

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1
         const val TABLE_USERS = "users"
         const val COLUMN_ID = "id"
         const val COLUMN_USERNAME = "username"
         const val COLUMN_EMAIL = "email"
         const val COLUMN_PASSWORD = "password"
         const val COLUMN_IMAGE_PROFILE = "image_profile"
         const val COLUMN_IMAGE_WALL = "image_wall"
    }

    override fun onCreate(db: SQLiteDatabase) {
            val createTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME VARCHAR(50),
                $COLUMN_EMAIL VARCHAR(100),
                $COLUMN_PASSWORD VARCHAR(100),
                $COLUMN_IMAGE_PROFILE TEXT,
                $COLUMN_IMAGE_WALL TEXT)
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun registerUser(username: String, email: String, password: String): Long {
        val contentValues = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        return writableDatabase.insert(TABLE_USERS, null, contentValues)
    }

    fun checkUsers(): List<Users> {
        val datalist = mutableListOf<Users>()
        val cursor = readableDatabase.query(TABLE_USERS, null, null, null, null, null, null)
        with(cursor){
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val username = getString(getColumnIndexOrThrow(COLUMN_USERNAME))
                val email = getString(getColumnIndexOrThrow(COLUMN_EMAIL))
                val password = getString(getColumnIndexOrThrow(COLUMN_PASSWORD))
                val profil = getString(getColumnIndexOrThrow(COLUMN_IMAGE_PROFILE))
                val wall = getString(getColumnIndexOrThrow(COLUMN_IMAGE_WALL))

                datalist.add(Users(id, username, email, password, profil, wall))
            }
        }
        cursor.close()
        return datalist
    }

    fun checkuserregist(username: String, password: String): Boolean {
        val users = checkUsers() // Mendapatkan semua pengguna dari database
        return users.any { it.username == username && it.password == password }
    }

    fun updateData(id: Int, username: String, email: String, password: String, profil: String, wall: String): Int {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_IMAGE_PROFILE, profil)
            put(COLUMN_IMAGE_WALL, wall)
        }

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        return writableDatabase.update(TABLE_USERS, values, selection, selectionArgs)
    }

    fun deleteData(id: Int): Int {
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(id.toString())

        return writableDatabase.delete(TABLE_USERS, selection, selectionArgs)
    }

    fun updateUserProfileImage(userId: Int, profileImageUri: String) {
        val db = writableDatabase
        // Menambahkan log untuk memeriksa nilai yang diterima
        Log.d("Database", "userId: $userId, profileImageUri: $profileImageUri")
        val contentValues = ContentValues()
        contentValues.put(COLUMN_IMAGE_PROFILE, profileImageUri)
        db.update(TABLE_USERS, contentValues, "$COLUMN_ID = ?", arrayOf(userId.toString()))
        val rowsUpdated = db.update(TABLE_USERS, contentValues, "$COLUMN_ID = ?", arrayOf(userId.toString()))
        Log.d("Database", "Rows updated: $rowsUpdated")
    }

    fun updateUserWallImage(userId: Int, wallImageUri: String) {
        val db = writableDatabase
        // Menambahkan log untuk memeriksa nilai yang diterima
        Log.d("Database", "userId: $userId, wallImageUri: $wallImageUri")
        val contentValues = ContentValues()
        contentValues.put(COLUMN_IMAGE_WALL, wallImageUri)
        db.update(TABLE_USERS, contentValues, "$COLUMN_ID = ?", arrayOf(userId.toString()))
        val rowsUpdated = db.update(TABLE_USERS, contentValues, "$COLUMN_ID = ?", arrayOf(userId.toString()))
        // Menambahkan log untuk memeriksa jumlah baris yang terupdate
        Log.d("Database", "Rows updated: $rowsUpdated")
    }

}
