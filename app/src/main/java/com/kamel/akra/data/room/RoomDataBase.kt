package com.kamel.akra.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kamel.akra.domain.entities.Prayer

@Dao
interface PrayersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrayer(vararg prayers: Prayer)

    @Query("SELECT * FROM prayer where timestamp >= (strftime('%s','now') * 1000) limit 1")
    fun getUpcomingPrayer(): LiveData<Prayer>

    @Query("select * from prayer where strftime('%d',datetime(timestamp / 1000, 'unixepoch')) = :dayNumber")
    fun getDayPrayers(dayNumber: String): LiveData<List<Prayer>>

    @Query("select * from prayer")
    fun getAll(): LiveData<List<Prayer>>

}

abstract class AppDatabase : RoomDatabase(){
    abstract val prayersDao: PrayersDao
}


private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase{
    synchronized(AppDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "akraa_app_database"
            ).createFromAsset("database/akraa_app_database10.db")
                .build()
        }
    }
    return INSTANCE
}
