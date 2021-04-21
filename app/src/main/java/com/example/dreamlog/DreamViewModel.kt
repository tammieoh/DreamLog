package com.example.dreamlog

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
//import java.sql.Date
import java.util.*

class DreamViewModel (private val repository: DreamRepository) : ViewModel(){
    val allDreams : LiveData<List<Dream>> = repository.allDreams.asLiveData()

    // we want to make sure that the view model is running in its own scope
    // in the viewmodel library, it has its own scope
    // viewModelScope
    // you want to launch a new coroutine to run each of the suspend function
    // from your repository. To use the viewModelScope, it allows everything to run based on
    // their lifecycle

    fun insert(dream: Dream) = viewModelScope.launch {
        repository.insert(dream)
    }

    fun updateDream(title: String, content: String, reflection: String, emotion: String, id: Int) = viewModelScope.launch {
        repository.updateDream(title, content, reflection, emotion, id)
    }

    fun deleteByTask(id: Int) = viewModelScope.launch {
        repository.deleteByID(id)
    }

    fun getDreamByID(id: Int) : LiveData<Dream> {
        return repository.getDreamByID(id).asLiveData()
    }
//
}


class DreamViewModelFactory(private val repository: DreamRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DreamViewModel::class.java)) {
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}