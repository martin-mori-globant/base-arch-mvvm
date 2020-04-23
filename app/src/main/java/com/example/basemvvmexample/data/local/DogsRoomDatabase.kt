package com.example.basemvvmexample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BreedRoom::class], version = 1, exportSchema = false)
public abstract class DogsRoomDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao

    companion object {
        @Volatile
        private var INSTANCE: DogsRoomDatabase? = null

        fun getDatabase(context: Context): DogsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsRoomDatabase::class.java,
                    "dogs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
