package com.example.contactmanagerapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var Id: Int,
    @ColumnInfo(name = "usr_name")
    var name: String,
    @ColumnInfo(name = "user_email")
    var email: String
)