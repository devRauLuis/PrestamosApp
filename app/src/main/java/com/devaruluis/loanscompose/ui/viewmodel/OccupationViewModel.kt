package com.devaruluis.loanscompose.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.loanscompose.database.dao.OccupationDao
import com.devaruluis.loanscompose.model.Occupation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OccupationViewModel @Inject constructor(val occupationDao: OccupationDao) : ViewModel() {

    val success = MutableLiveData<Boolean>()
    val occupationLD = MutableLiveData<Occupation>()
    val occupations = MutableLiveData<List<Occupation>>()
    val error = MutableLiveData<Exception>()

    fun save(occupation: Occupation) {
        insert(occupation)
//        else update(occupation)
    }

    fun insert(occupation: Occupation) {
        viewModelScope.launch {
            try {
                val id = occupationDao.insert(occupation)
                find(id)
                success.postValue(true)
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun update(occupation: Occupation) {
        viewModelScope.launch {
            try {
                occupationDao.update(occupation)
                find(occupation.id)
                success.postValue(true)
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun find(id: Long?) {
        viewModelScope.launch {
            try {
                val found = occupationDao.find(id)
                occupationLD.postValue(found)
                success.postValue(true)
            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun deleteCurrentOccupation() {
        delete(occupationLD.value?.id)
    }

    fun delete(id: Long?) {
        viewModelScope.launch {
            try {
                if (id != null) {
                    val found = occupationDao.find(id)
                    occupationDao.delete(found)
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
                val occupationList = occupationDao.getAll()
                occupations.postValue(occupationList)
                success.postValue(true)

            } catch (e: Exception) {
                error.postValue(e)
            }
        }
    }

    fun clear() {
        viewModelScope.launch {
            occupationLD.postValue(null)
        }
    }
}