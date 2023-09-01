package com.example.contactmanagerapp

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: Repository) : ViewModel(), Observable {
    val users = repository.users
    private var isUpdate0rDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (isUpdate0rDelete) {
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            update(userToUpdateOrDelete)


        } else {
            //Insert functionality
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(User(0, name, email))
            inputName.value = null
            inputEmail.value = null

        }

    }

    fun insert(user: User) = viewModelScope.launch {
        repository.addUserToTheRoom(user)
    }

    fun clearAllorDelete() {
        if (isUpdate0rDelete) {
            delete(userToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user: User) = viewModelScope.launch {
        repository.updateUserInTheRoom(user)
        inputName.value=null
        inputEmail.value=null
        isUpdate0rDelete=false
        saveOrUpdateButtonText.value="Save"
        clearAllDeleteButtonText.value="Clear"
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.deleteUserFromTheRoom(user)
        repository.updateUserInTheRoom(user)
        inputName.value=null
        inputEmail.value=null
        isUpdate0rDelete=false
        saveOrUpdateButtonText.value="Save"
        clearAllDeleteButtonText.value="Clear"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun initUpdateAndDelete(selectedItem: User) {
         inputName.value=selectedItem.name
        inputEmail.value=selectedItem.email
        isUpdate0rDelete=true
        userToUpdateOrDelete=selectedItem
        saveOrUpdateButtonText.value="Update"
        clearAllDeleteButtonText.value="Delete"
    }


}