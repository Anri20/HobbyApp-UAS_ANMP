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

class RegisterViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val accountLDCompare = MutableLiveData<List<Account>>()
    val accountLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun compare(username: String) {
        accountLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            var db = buildDb(getApplication())
            accountLDCompare.postValue(db.accountDao().compare(username))
        }
    }

    fun register(
        nama_depan: String,
        nama_belakang: String,
        username: String,
        password: String,
        imgUrl: String?
    ) {
        launch {
            buildDb(getApplication()).accountDao()
                .insert(
                    Account(
                        namaDepan = nama_depan,
                        namaBelakang = nama_belakang,
                        username = username,
                        password = password,
                        imgUrl = imgUrl
                    )
                )
        }
    }
}