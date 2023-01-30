package com.google.groceryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.todo.todoRepository

class todoViewModelFactory(private val repository: todoRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return todoViewModel(repository) as T
    }


}