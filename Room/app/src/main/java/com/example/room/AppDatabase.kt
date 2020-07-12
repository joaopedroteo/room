package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.users.User
import com.example.room.users.UserDao

@Database(
    version = 2, entities = [
        Task::class,
        User::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDAO

    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE:AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app-database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}