package com.example.hobbyapp_uasanmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp_uasanmp.model.Account
import com.example.hobbyapp_uasanmp.model.AccountDao
import com.example.hobbyapp_uasanmp.model.AppDatabase
import com.example.hobbyapp_uasanmp.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val accountLD = MutableLiveData<List<Account>>()
    val accountLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private suspend fun isDbReady(): Boolean {
        return try {
            buildDb(getApplication()).accountDao().getAll().firstOrNull() != null
        } catch (e: Exception) {
            false
        }
    }

    suspend fun initializeDatabase() {
        while (!isDbReady()) {
            delay(1000)
        }

        launch {
            buildDb(getApplication())
        }
    }

    fun login(username: String, password: String) {
        accountLoadErrorLD.value = false
        loadingLD.value = true

        launch {
            val db = buildDb(getApplication())
            Log.d("login", db.accountDao().loginAccount(username, password).toString())
            accountLD.postValue(db.accountDao().loginAccount(username, password))
        }
    }
}