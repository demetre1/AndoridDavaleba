package com.example.reqres

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.util.*


class MyAdapter(private val datalist: MutableList<Date>): RecyclerView.Adapter<MyHolder>() {

    private lateinit var context : Context


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolder {


       context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }


    override fun getItemCount(): Int = datalist.size


    override fun onBindViewHolder(holder: MyHolder, position: Int) {

       val data = datalist[position]

        val userFullName = holder.itemView.findViewById<TextView>(R.id.user_full_name)
        val userAvatart = holder.itemView.findViewById<ImageView>(R.id.user_avatar)
        val fullName = "${data.first_name} ${data.last_name}"
        userFullName.text = fullName

        Picasso.get()
            .load(data.avatar)
            .into(userAvatart)

        holder.itemView.setOnClickListener{
            Toast.makeText(context,fullName,Toast.LENGTH_SHORT).show()
        }

        
        
    }
}