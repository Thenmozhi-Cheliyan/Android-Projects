package com.example.details.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities=[Details::class],version=1, exportSchema = false)
abstract class DetailsDatabase: RoomDatabase() {

    companion object{
        private var INSTANCE:DetailsDatabase?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context):DetailsDatabase?{
            synchronized(DetailsDatabase ::class.java) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            DetailsDatabase::class.java,
                            "details_database"
                        ).build()
                    return INSTANCE
                }
            }
            return INSTANCE
        }

    }
    abstract fun detailsDao():DetailsDao
}