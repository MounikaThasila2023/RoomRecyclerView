package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.model.DataItems
import com.example.roomrecyclerview.R

class RecycleAdapter( private val context: Context, private var empList: List<DataItems>, private val mRowLayout: Int) :
    RecyclerView.Adapter<RecycleAdapter.Viewholder>() {
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userId = itemView.findViewById<TextView>(R.id.user_id)
        val id = itemView.findViewById<TextView>(R.id.id)
        var emp_title = itemView.findViewById<TextView>(R.id.title)
        var emp_body = itemView.findViewById<TextView>(R.id.body)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val v = LayoutInflater.from(context).inflate(mRowLayout, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.userId.text = empList[position].userId.toString()
        holder.id.text = empList[position].id.toString()
        holder.emp_title.text = empList[position].title
        holder.emp_body.text = empList[position].body
    }

    override fun getItemCount(): Int {
        return empList.size
    }

}