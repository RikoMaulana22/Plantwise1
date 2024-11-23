package com.example.planwise1

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension untuk DataStore
val Context.dataStore by preferencesDataStore(name = "user_preferences")

class DataStoreManager(private val context: Context) {
    private val NOTE_KEY = stringPreferencesKey("note_key")

    // Fungsi untuk menyimpan catatan
    suspend fun saveNote(note: String) {
        context.dataStore.edit { preferences ->
            preferences[NOTE_KEY] = note
        }
    }

    // Fungsi untuk membaca catatan
    val noteFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[NOTE_KEY] ?: ""
    }
}
