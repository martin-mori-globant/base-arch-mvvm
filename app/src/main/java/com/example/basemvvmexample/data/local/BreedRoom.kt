package com.example.basemvvmexample.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
data class BreedRoom(@PrimaryKey @ColumnInfo(name = "breed") val breed: String)
