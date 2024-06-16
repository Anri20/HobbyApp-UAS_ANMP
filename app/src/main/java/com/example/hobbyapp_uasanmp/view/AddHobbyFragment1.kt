package com.example.hobbyapp_uasanmp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp_uasanmp.R
import com.example.hobbyapp_uasanmp.databinding.FragmentAddHobby1Binding
import com.example.hobbyapp_uasanmp.databinding.FragmentAddHobby2Binding
import com.example.hobbyapp_uasanmp.util.loadImage
import com.example.hobbyapp_uasanmp.viewmodel.HobbyViewModel

class AddHobbyFragment1 : Fragment() {
    private var _binding: FragmentAddHobby1Binding? = null
    private val binding get() = _binding!!

    private lateinit var hobbyViewModel: HobbyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddHobby1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hobbyViewModel = ViewModelProvider(this).get(HobbyViewModel::class.java)

        binding.txtURLAdd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.imgAddHobby1.loadImage(p0.toString())
            }
        })

        binding.btnNextAdd.setOnClickListener {
            var title = binding.txtTitleAdd.text.toString()
            var preview = binding.txtPreviewAdd.text.toString()

            if (title.isNotEmpty()){
                if (preview.isNotEmpty()){
                    val action = AddHobbyFragment1Directions.actionAddHobby2(binding.txtURLAdd.text.toString(), title, preview)
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(context, "Preview is required", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Title is required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}