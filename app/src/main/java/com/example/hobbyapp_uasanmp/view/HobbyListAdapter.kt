package com.example.hobbyapp_uasanmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp_uasanmp.R
import com.example.hobbyapp_uasanmp.model.Hobby
import com.example.hobbyapp_uasanmp.util.loadImage

class HobbyListAdapter(val hobbyList: ArrayList<Hobby>) :
    RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>() {
    class HobbyViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hobby_list_item, parent, false)

        return HobbyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbyList.size
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.imgHobby).loadImage(hobbyList[position].imgUrl)
        holder.view.findViewById<TextView>(R.id.txtTitle).text = hobbyList[position].title
        holder.view.findViewById<TextView>(R.id.txtWriter).text = "@${hobbyList[position].writer}"
        holder.view.findViewById<TextView>(R.id.txtPreview).text = hobbyList[position].preview

        holder.view.findViewById<Button>(R.id.btnRead).setOnClickListener {
            val action = HobbyListFragmentDirections.actionHobbyDetail(
                hobbyList[position].imgUrl.toString(),
                hobbyList[position].title.toString(),
                hobbyList[position].writer.toString(),
                hobbyList[position].content
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateHobbyList(newHobbyList: ArrayList<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }
}