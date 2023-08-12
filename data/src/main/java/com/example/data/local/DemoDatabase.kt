package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.entity.DemoEntity
import com.example.data.util.DATABASE_NAME

@Database(entities = [DemoEntity::class], version = 1, exportSchema = false)
abstract class DemoDatabase : RoomDatabase() {

    abstract val demoDao: DemoDao

    companion object {
        private lateinit var INSTANCE: DemoDatabase
        fun getInstance(context: Context): DemoDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DemoDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}