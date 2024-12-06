package com.example.planwise1.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Users(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val profil: String?,
    val wall: String?,
): Parcelable
