package com.example.hobbyapp_uasanmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp_uasanmp.databinding.FragmentProfileBinding
import com.example.hobbyapp_uasanmp.model.Account
import com.example.hobbyapp_uasanmp.util.GlobalData
import com.example.hobbyapp_uasanmp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(), ProfileUpdateClicked, ProfileLogoutClicked {
//    private var _binding: FragmentProfileBinding? = null
//    private val binding get() = _binding!!

    private lateinit var dataBinding: FragmentProfileBinding

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var account: Account


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.updateListener = this
        dataBinding.logoutListener = this

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        account = GlobalData.account

        profileViewModel.getProfile(account.idAccount.toInt())

        observeViewModel()
    }

    private fun observeViewModel() {
        profileViewModel.profileLD.observe(viewLifecycleOwner, Observer {
            Log.d("showObserveVM", it.toString())
            Log.d("imgURL", (it[0].imgUrl.toString() == "").toString())
            dataBinding.account = it[0]
        })
    }

    override fun onClickUpdate(view: View, account: Account) {
        Log.d("updateAccount", account.toString())
        profileViewModel.updateProfile(account)
        Toast.makeText(view.context, "Profile update successfully", Toast.LENGTH_SHORT).show()
        GlobalData.account = account
    }

    override fun onClickLogout(view: View) {
        val action = ProfileFragmentDirections.actionLogout()
        Navigation.findNavController(view).navigate(action)
    }
}