package com.example.planwise1.data

import android.content.Context
import com.example.planwise1.data.PreferencerKey.NAME_PREF
import com.example.planwise1.data.PreferencerKey.PASSWORD_KEY
import com.example.planwise1.data.PreferencerKey.NAME_KEY
import com.example.planwise1.data.PreferencerKey.EMAIL
import com.example.planwise1.data.PreferencerKey.PROFILE
import com.example.planwise1.data.PreferencerKey.WALL

class SharedPrePreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    var name
        get() = preferences.getString(NAME_KEY, "")
        set(value) {
            editor.putString(NAME_KEY, value)
            editor.commit()
        }

    var email
        get() = preferences.getString(EMAIL, "")
        set(value) {
            editor.putString(EMAIL, value)
            editor.commit()
        }

    var password
        get() = preferences.getString(PASSWORD_KEY, "")
        set(value) {
            editor.putString(PASSWORD_KEY, value)
            editor.commit()
        }

    var wall: String?
        get() = preferences.getString("wall", null)
        set(value) {
            preferences.edit().putString("wall", value).apply()
        }

    var profil: String?
        get() = preferences.getString("profil", null)
        set(value) {
            preferences.edit().putString("profil", value).apply()
        }


    // Menyimpan userId ke SharedPreferences
    fun setUserId(userId: Int) {
        editor.putInt("user_id", userId)
        editor.apply()
    }

    // Mengambil userId dari SharedPreferences
    fun getUserId(): Int {
        return preferences.getInt("user_id", -1) // Mengembalikan -1 jika userId belum diset
    }


    fun clear() {
        editor.clear()
        editor.commit()
    }
}