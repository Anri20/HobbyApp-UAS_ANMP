package com.example.hobbyapp_uasanmp.view

import com.example.hobbyapp_uasanmp.model.Account

interface BottomNavigationVisibilityListener {
    fun setBottomNavigationVisibility(isVisible: Boolean)
}

interface OnDataPassListener {
    fun onDataPass(data: ArrayList<Account>)
}
