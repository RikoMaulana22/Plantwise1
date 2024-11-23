package com.example.planwise1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JadwalViewModel : ViewModel() {
    private val _text = MutableStateFlow<String>("")
    val text: StateFlow<String?> = _text

    fun saveText(newText: String) {
        _text.value = newText
    }
}
