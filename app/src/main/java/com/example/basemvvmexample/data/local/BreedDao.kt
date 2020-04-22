package com.example.basemvvmexample.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {

    @Query("SELECT * from breed_table ORDER BY breed ASC")
    fun getBreedsAlphabeticOrder(): LiveData<List<BreedRoom>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(breeds: List<BreedRoom>)

    @Query("DELETE from breed_table")
    suspend fun deleteAll()
}
