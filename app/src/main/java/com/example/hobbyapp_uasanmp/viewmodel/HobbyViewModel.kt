package com.example.hobbyapp_uasanmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp_uasanmp.model.Hobby
import com.example.hobbyapp_uasanmp.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HobbyViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val hobbyLD = MutableLiveData<List<Hobby>>()
    val hobbyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getHobby() {
        loadingLD.value = true
        hobbyLoadErrorLD.value = false

        launch {
            val db = buildDb(getApplication())
            hobbyLD.postValue(db.hobbyDao().selectAllHobby())
        }
    }
}