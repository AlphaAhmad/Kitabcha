package com.mkrdeveloper.viewmodeljetpack.app.kitabcha.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkrdeveloper.viewmodeljetpack.app.kitabcha.data.repository.UserRepository
import app.kitabcha.data.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    fun insertUser(user: UserEntity) {
        viewModelScope.launch(IO) {
            repository.insert(user)
        }
    }

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()
    fun setUserName(name: String) {
        _userName.tryEmit(name)
    }

    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()
    fun setUserPassword(name: String) {
        _userPassword.tryEmit(name)
    }




}