package com.google.todo

import  android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class todoRVAdapter(var list: List<todoItems>, val TodoItemClickInterface: todoItemClickInterface) : RecyclerView.Adapter<todoRVAdapter.todoViewHolder>() {



    inner class todoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTV = itemView.findViewById<TextView>(R.id.idTVItemName)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idTVDelete)
    }


    interface todoItemClickInterface{
        fun onItemClick(todoItems: todoItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_rv_item,parent,false)
        return todoViewHolder(view)
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        holder.nameTV.text = list.get(position).itemName
        holder.deleteTV.setOnClickListener{
            TodoItemClickInterface.onItemClick(list.get(position))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}