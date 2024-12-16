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
    private val TEXT_FIELD_1 = stringPreferencesKey("text_field_1")
    private val TEXT_FIELD_2 = stringPreferencesKey("text_field_2")
    private val TEXT_FIELD_3 = stringPreferencesKey("text_field_3")
    private val TEXT_FIELD_4 = stringPreferencesKey("text_field_4")


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

    // Fungsi untuk menyimpan data TextField
    suspend fun saveTextFields(value1: String, value2: String, value3: String, value4: String) {
        context.dataStore.edit { preferences ->
            preferences[TEXT_FIELD_1] = value1
            preferences[TEXT_FIELD_2] = value2
            preferences[TEXT_FIELD_3] = value3
            preferences[TEXT_FIELD_4] = value4
        }
    }
    // Fungsi untuk membaca data TextField
    val textFieldsFlow: Flow<List<String>> = context.dataStore.data.map { preferences ->
        listOf(
            preferences[TEXT_FIELD_1] ?: "",
            preferences[TEXT_FIELD_2] ?: "",
            preferences[TEXT_FIELD_3] ?: "",
            preferences[TEXT_FIELD_4] ?: ""
        )
    }
}
