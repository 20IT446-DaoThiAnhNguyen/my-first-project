package com.example.sqlite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var stdList: ArrayList<StudentModel> = ArrayList()

    fun addItems(items: ArrayList<StudentModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StudentViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
    )

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
    }

    override fun getItemCount(): Int {
        return stdList.size
    }
    class StudentViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var email = view.findViewById<TextView>(R.id.tvEmail)
        private var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(std:StudentModel){
            id.text = std.id.toString()
            name.text = std.name
            email.text = std.email

        }
    }
}