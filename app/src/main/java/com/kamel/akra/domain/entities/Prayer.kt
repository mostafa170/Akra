package com.kamel.akra.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prayer")
data class Prayer(
    @PrimaryKey()
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "date_hijri")
    val dateHijri: String,
    @ColumnInfo(name = "timestamp")
    val dateTime: Long,
    @ColumnInfo(name = "remaining")
    val remainingTime: Long
)
