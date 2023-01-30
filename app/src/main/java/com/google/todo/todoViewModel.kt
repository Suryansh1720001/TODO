package com.google.todo

import androidx.lifecycle.ViewModel
import com.google.todo.todoItems
import com.google.todo.todoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class todoViewModel(private val repository: todoRepository) : ViewModel() {


    fun insert(items: todoItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items: todoItems) = GlobalScope.launch {
        repository.delete(items)

    }

    fun getAlltodoItems() = repository.getAllItems()

}