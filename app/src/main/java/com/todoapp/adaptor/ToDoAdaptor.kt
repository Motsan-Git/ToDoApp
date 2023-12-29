package com.todoapp.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.todoapp.R
import com.todoapp.fragment.binding
import com.todoapp.model.ToDo

class ToDoAdaptor(var todoList: MutableList<ToDo>, var context: Context) :
    RecyclerView.Adapter<ToDoAdaptor.ViewHolder>() {
    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        var card = itemView.findViewById<CardView>(R.id.card)
        var titleTV = itemView.findViewById<TextView>(R.id.titleCardTV)
        var descriptionTB = itemView.findViewById<TextView>(R.id.descriptionCTB)
        var dateTV = itemView.findViewById<TextView>(R.id.dateCardTV)
        var dateTB = itemView.findViewById<TextView>(R.id.dateCardTB)
        var timeTV = itemView.findViewById<TextView>(R.id.timeCardTV)
        var timeTB = itemView.findViewById<TextView>(R.id.timeCardTB)
        var isDoneCheckBox = itemView.findViewById<CheckBox>(R.id.isDone)
        init {
            isDoneCheckBox.setOnCheckedChangeListener{button, isSelected ->
                if(isSelected){
                    todoList.removeAt(adapterPosition)
                   binding.recView.adapter!!.notifyDataSetChanged()

                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).
             inflate(R.layout.item_in_rec_view,parent,false)
         return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            timeTB.text=todoList[position].title
            descriptionTB.text=todoList[position].title
            dateTB.text=todoList[position].date
            timeTB.text=todoList[position].time
            isDoneCheckBox.isChecked=todoList[position].isDone
        }

    }


}

