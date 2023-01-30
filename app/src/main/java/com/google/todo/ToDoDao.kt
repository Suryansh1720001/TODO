package com.google.todo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(item : todoItems)

    @Delete
 suspend fun delete(item: todoItems)


    @Query("SELECT * fROM todo_items")
   fun getAlltodoItems() : LiveData<List<todo>>

}