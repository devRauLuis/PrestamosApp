package com.devaruluis.prestamos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.prestamos.data.database.dao.PersonDao
import com.devaruluis.prestamos.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(val personDao: PersonDao) : ViewModel() {

     val success = MutableLiveData<Boolean>()

    fun save(person: Person) {
        viewModelScope.launch {
                personDao.insert(person)
                success.postValue(true)
        }
    }
}