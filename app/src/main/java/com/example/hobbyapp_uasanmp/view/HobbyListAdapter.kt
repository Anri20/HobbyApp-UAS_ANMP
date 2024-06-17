package com.example.hobbyapp_uasanmp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp_uasanmp.R
import com.example.hobbyapp_uasanmp.databinding.HobbyListItemBinding
import com.example.hobbyapp_uasanmp.model.Hobby
import com.example.hobbyapp_uasanmp.model.HobbyAccount
import com.example.hobbyapp_uasanmp.util.loadImage

class HobbyListAdapter(private val hobbyList: ArrayList<HobbyAccount>) :
    RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>(), HobbyReadClicked {
    class HobbyViewHolder(var view: HobbyListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = DataBindingUtil.inflate<HobbyListItemBinding>(
//            inflater,
//            R.layout.hobby_list_item,
//            parent,
//            false
//        )
        val view = HobbyListItemBinding.inflate(inflater, parent, false)


        return HobbyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbyList.size
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        with(holder.view){
            hobbyAccount = hobbyList[position]
            readListener = this@HobbyListAdapter
        }
    }

    fun updateHobbyList(newHobbyList: ArrayList<HobbyAccount>) {
        hobbyList.clear()
        hobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }

    override fun onClickRead(view: View, hobbyAccount: HobbyAccount) {
        val action = HobbyListFragmentDirections.actionHobbyDetail(
            hobbyAccount.hobby.imgUrl.toString(),
            hobbyAccount.hobby.title.toString(),
            hobbyAccount.account.username.toString(),
            hobbyAccount.hobby.content
        )
        Navigation.findNavController(view).navigate(action)
    }
}