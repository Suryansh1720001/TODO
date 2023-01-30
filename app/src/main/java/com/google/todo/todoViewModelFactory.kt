package com.google.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class todoViewModelFactory(private val repository: todoRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return todoViewModel(repository) as T
    }


}