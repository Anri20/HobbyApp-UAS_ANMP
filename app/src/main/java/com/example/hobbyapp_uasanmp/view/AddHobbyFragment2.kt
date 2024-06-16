package com.example.hobbyapp_uasanmp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.hobbyapp_uasanmp.databinding.FragmentAddHobby2Binding
import com.example.hobbyapp_uasanmp.model.Account
import com.example.hobbyapp_uasanmp.util.GlobalData
import com.example.hobbyapp_uasanmp.util.isIndexValid
import com.example.hobbyapp_uasanmp.util.loadImage
import com.example.hobbyapp_uasanmp.viewmodel.HobbyViewModel

class AddHobbyFragment2 : Fragment() {
    private var _binding: FragmentAddHobby2Binding? = null
    private val binding get() = _binding!!

    private lateinit var hobbyViewModel: HobbyViewModel

    private var account: Account = GlobalData.account
    private lateinit var imgUrl: String
    private lateinit var title: String
    private lateinit var preview: String

    private var content: ArrayList<String> = arrayListOf()
    private lateinit var currContent: String

    private var maxPage: Int = 10
    private var currPage: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddHobby2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hobbyViewModel = ViewModelProvider(this).get(HobbyViewModel::class.java)

        arguments.let {
            imgUrl = AddHobbyFragment2Args.fromBundle(requireArguments()).imgUrl
            title = AddHobbyFragment2Args.fromBundle(requireArguments()).title
            preview = AddHobbyFragment2Args.fromBundle(requireArguments()).preview
        }

        binding.imgAddHobby2.loadImage(imgUrl)
        binding.txtAddTitle.text = title
        binding.txtAddWriter.text = "@${account.username}"

        binding.txtPage.text = "${currPage} / ${maxPage}"

        binding.btnPrevPage.setOnClickListener {
            if (currPage > 1) {
                if (binding.txtContentAdd.text!!.isNotEmpty()) {
                    if (isIndexValid(content, currPage - 1)) {
                        content[currPage - 1] = binding.txtContentAdd.text.toString()
                    } else {
                        content.add(currPage - 1, binding.txtContentAdd.text.toString())
                    }
                } else {
                    Toast.makeText(
                        context,
                        "The content is empty, please fill the content first",
                        Toast.LENGTH_LONG
                    ).show()
                }

                binding.txtContentAdd.setText(content[currPage - 2])
                Log.d("content", content.toString())

                currPage -= 1
                binding.txtPage.text = "${currPage} / ${maxPage}"

            } else {
                Toast.makeText(context, "You've reached the first page.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnAddPage.setOnClickListener {
//            if the content in current page is not null or not an empty string
            if (binding.txtContentAdd.text!!.isNotEmpty()) {
//                add the content to the responsible index
                if (isIndexValid(content, currPage - 1)) {
                    content[currPage - 1] = binding.txtContentAdd.text.toString()
                } else {
                    content.add(currPage - 1, binding.txtContentAdd.text.toString())
                }

                if (currPage < maxPage) {
//                    if there is already a content in next page then display
                    if (content.size > currPage) {
                        binding.txtContentAdd.setText(content[currPage])
                    }
//                    else just clear the current edittext
                    else {
                        binding.txtContentAdd.text!!.clear()
                    }
                    Log.d("content", content.toString())


                    currPage += 1
                    binding.txtPage.text = "${currPage} / ${maxPage}"
                } else {
                    Toast.makeText(context, "You've reached the last page.", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    context,
                    "The content is still empty, please fill the content first",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.btnDone.setOnClickListener {
            hobbyViewModel.addHobby(
                imgUrl = imgUrl,
                title = title,
                writer = account.username.toString(),
                preview = preview,
                content = content.joinToString("; "),
                idAccount = account.idAccount
            )

            val action = AddHobbyFragment2Directions.actionAddDone()
            Navigation.findNavController(requireView()).navigate(action)

            Toast.makeText(context, "Hobby Added Successfully", Toast.LENGTH_SHORT).show()
        }
    }
}