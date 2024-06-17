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
import com.example.hobbyapp_uasanmp.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.nio.charset.Charset
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val profileLD = MutableLiveData<ArrayList<Account>>()
    val profileLoadErrorLD = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getProfile(id: Int) {
        profileLoadErrorLD.value = false

        launch {
            val db = buildDb(getApplication())
            profileLD.postValue(db.accountDao().getAccount(id).toCollection(arrayListOf()))
        }
    }

    fun updateProfile(account: Account) {
        launch {
            buildDb(getApplication()).accountDao().update(account)
        }
    }
}