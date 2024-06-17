package com.example.hobbyapp_uasanmp.view

import android.view.View
import com.example.hobbyapp_uasanmp.model.Account
import com.example.hobbyapp_uasanmp.model.HobbyAccount

interface BottomNavigationVisibilityListener {
    fun setBottomNavigationVisibility(isVisible: Boolean)
}

interface OnDataPassListener {
    fun onDataPass(data: ArrayList<Account>)
}

interface HobbyReadClicked {
    fun onClickRead(view: View, hobbyAccount: HobbyAccount)
}

interface ProfileUpdateClicked {
    fun onClickUpdate(view: View, account: Account)
}

interface ProfileLogoutClicked {
    fun onClickLogout(view: View)
}
