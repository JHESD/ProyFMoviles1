package com.example.proyfinalmv2.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyfinalmv2.repositories.items.LogReg.LoginuserRepository
import com.example.proyfinalmv2.repositories.items.LogReg.TokenRepository

class MainViewModel: ViewModel() {
    // Error
    private val _errorMessage: MutableLiveData<String> by lazy {
        // se inicializa el MutableLiveData
        MutableLiveData<String>("")
    }
    val errorMessage: LiveData<String> get() = _errorMessage
    fun login(
        email: String,
        password: String,
        context: Context
    ) {
        LoginuserRepository.doLogin(
            email,
            password,
            success = {
                if(it == null) {
                    _errorMessage.value = "Usuario o contraseña incorrectos"
                    return@doLogin
                }
                _errorMessage.value = ""
                Log.d("MainViewModel", "Token: ${it.accessToken}")
                val token: String = it.accessToken!!
                TokenRepository.saveToken(token, context)
            }, failure = {
                it.printStackTrace()
            })
    }

}