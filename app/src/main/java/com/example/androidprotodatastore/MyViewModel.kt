package com.example.androidprotodatastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidprotodatastore.data.ProtoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProtoRepository(application)

    val info = repository.read.asLiveData()

    fun updateContact(name : String,age : Int,job : String) = viewModelScope.launch(Dispatchers.IO) {
        repository.write(name,age,job)
    }
}