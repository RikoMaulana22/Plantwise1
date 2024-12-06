package com.example.planwise1.data

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencerKey {
    const val NAME_PREF = "login_preferences"
    const val NAME_KEY = "name"
    const val EMAIL = "email"
    const val PASSWORD_KEY = "password"
    const val PROFILE = "profil"
    const val WALL = "wall"

    val STATUS_LOGIN_KEY = booleanPreferencesKey("status_login")
}