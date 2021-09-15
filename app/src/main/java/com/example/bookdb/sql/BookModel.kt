package com.example.bookdb.sql

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class BookModel(
    var id: Int = getAutoId(),
    val Title: String,
    val Author: String,
    val Genre: String,
) : Parcelable {
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}
