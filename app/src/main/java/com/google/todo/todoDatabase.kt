package com.google.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [todoItems::class],version=1)
abstract class todoDatabase : RoomDatabase() {

    abstract fun gettodoDao():ToDoDao

    companion object {
        @Volatile
        private var instance: todoDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{
                instance=it
            }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                todoDatabase::class.java,
                "Grocery.db"
            ).build()
    }
}