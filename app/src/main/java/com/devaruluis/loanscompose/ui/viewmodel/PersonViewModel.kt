package com.devaruluis.loanscompose.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.loanscompose.database.dao.PersonDao
import com.devaruluis.loanscompose.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(val personDao: PersonDao) : ViewModel() {

    val success = MutableLiveData<Boolean>()
    val personLD = MutableLiveData<Person>()
    val peopleLD = MutableLiveData<List<Person>>()
    val error = MutableLiveData<Exception>()

//    var person by mutableStateOf(Person())
//        private set

    fun save(person: Person) {
        insert(person)
//        else update(person)
    }

    fun insert(person: Person) {
        viewModelScope.launch {
            try {
                val id = personDao.insert(person)
                find(id)
                if (personLD.value != null)
                    success.postValue(true)
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun update(person: Person) {
        viewModelScope.launch {
            try {
                personDao.update(person)
                find(person.id)
                success.postValue(true)
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun find(id: Long?) {
        viewModelScope.launch {
            val found = personDao.find(id)
            personLD.postValue(found)
            success.postValue(true)
        }
    }

    fun deleteCurrentPerson() {
        delete(personLD.value?.id)
    }

    fun delete(id: Long?) {
        viewModelScope.launch {
            try {
                if (id != null) {
                    val found = personDao.find(id)
                    personDao.delete(found)
                    success.postValue(true)
                }
            } catch (e: Exception) {
                error.postValue(e)
            }

        }
    }

    fun getAll() {
        viewModelScope.launch {
            try {
                val peopleList = personDao.getAll()
                peopleLD.postValue(peopleList)
                success.postValue(true)

            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun clear() {
        viewModelScope.launch {
            personLD.postValue(null)
        }
    }

}