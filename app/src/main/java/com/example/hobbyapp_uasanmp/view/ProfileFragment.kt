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
import com.example.hobbyapp_uasanmp.util.loadImage
import com.example.hobbyapp_uasanmp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var account: Account


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        account = GlobalData.account

        profileViewModel.getProfile(account.idaccount.toString())

        observeViewModel()

        binding.btnUpdate.setOnClickListener {
            profileViewModel.updateProfile(
                account.idaccount.toString(),
                binding.txtProfileNamaDepan.text.toString(),
                binding.txtProfileNamaBelakang.text.toString(),
                binding.txtProfileUsername.text.toString(),
                binding.txtProfilePassword.text.toString(),
                account.imgUrl.toString()
            )

            GlobalData.account = Account(
                account.idaccount,
                binding.txtProfileNamaDepan.text.toString(),
                binding.txtProfileNamaBelakang.text.toString(),
                binding.txtProfileUsername.text.toString(),
                binding.txtProfilePassword.text.toString(),
                account.imgUrl.toString()
            )

            Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            val action = ProfileFragmentDirections.actionLogout()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        profileViewModel.profileLD.observe(viewLifecycleOwner, Observer {
            Log.d("showObserveVM", it.toString())
            Log.d("imgURL", (it[0].imgUrl.toString() == "null").toString())
            if (it[0].imgUrl != "null"){
                binding.ProfilePict.loadImage(it[0].imgUrl)
                binding.ProfilePict.visibility = View.VISIBLE
                binding.defaultPict.visibility = View.INVISIBLE
            } else{
                binding.ProfilePict.visibility = View.INVISIBLE
                binding.defaultPict.visibility = View.VISIBLE
            }
            binding.txtProfileUsername.text = it[0].username.toString()
            binding.txtProfilePassword.setText(it[0].password)
            binding.txtProfileNamaDepan.setText(it[0].nama_depan)
            binding.txtProfileNamaBelakang.setText(it[0].nama_belakang)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}