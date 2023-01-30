package com.google.todo

class todoRepository(private val db:todoDatabase) {

    suspend fun insert(items:todoItems) =db.gettodoDao().insert(items)
    suspend fun delete(items: todoItems) = db.gettodoDao().delete(items)

    fun getAllItems() = db.gettodoDao().getAlltodoItems()

}