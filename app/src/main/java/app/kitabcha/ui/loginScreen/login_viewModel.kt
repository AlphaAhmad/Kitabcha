package com.mkrdeveloper.viewmodeljetpack.app.kitabcha.ui.loginScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.kitabcha.data.MainDatabase
import app.kitabcha.data.User
import app.kitabcha.ui.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class login_viewModel(application: Application): AndroidViewModel(application) {

    var name_of_User:String="0"
    var password_received:String="0"
    fun getUserData(name_received:String,password_coming:String)
    {
        name_of_User = name_received
        password_received=password_coming

        var userX = User(0,name_of_User,password_received)
        addUser(userX)
    }

    private val repository: Repository

    init{
        val userdao=MainDatabase.getDataBase(application).userDao()
        repository=Repository(userdao)
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }
}

