package com.example.hobbyapp_uasanmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp_uasanmp.model.Account
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.nio.charset.Charset

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    val profileLD = MutableLiveData<ArrayList<Account>>()
    val profileLoadErrorLD = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

    fun getProfile(id: String) {

    }

    fun updateProfile(
        id: String,
        nama_depan: String,
        nama_belakang: String,
        username: String,
        password: String,
        imgUrl: String
    ) {

    }
}