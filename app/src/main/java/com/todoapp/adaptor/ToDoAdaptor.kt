package com.todoapp.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.todoapp.R
import com.todoapp.fragment.binding
import com.todoapp.fragment.dataStore
import com.todoapp.model.ToDo
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

class ToDoAdaptor(var todoList: MutableList<ToDo>, var context: Context) :
    RecyclerView.Adapter<ToDoAdaptor.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card = itemView.findViewById<CardView>(R.id.card)
        var titleTV = itemView.findViewById<TextView>(R.id.titleCardTV)
        var descriptionTB = itemView.findViewById<TextView>(R.id.descriptionCTB)
        var dateTV = itemView.findViewById<TextView>(R.id.dateCardTV)
        var dateTB = itemView.findViewById<TextView>(R.id.dateCardTB)
        var timeTV = itemView.findViewById<TextView>(R.id.timeCardTV)
        var timeTB = itemView.findViewById<TextView>(R.id.timeCardTB)
        var isDoneCheckBox = itemView.findViewById<CheckBox>(R.id.isDone)

        init {
            isDoneCheckBox.setOnCheckedChangeListener { button, isSelected ->
                if (isSelected) {
                    val isdone = adapterPosition
                    if (isdone != RecyclerView.NO_POSITION) {
                        val todo = todoList[adapterPosition]
                        todoList.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                        runBlocking {
                            context.dataStore.updateData {
                                it.copy(
                                    todoList = it.todoList.mutate {
                                        it.remove(todo)

                                        /*context.dataStore.updateData {
                                            it.copy(
                                                it.todoList.mutate {
                                                    it.removeAt(adapterPosition)**/
                                    }
                                )
                            }
                            //binding.recView.adapter!!.notifyItemRemoved(adapterPosition)
                            // این جا برای حذف آنی کارد خودمون با کلیک بر ایز دان مشکل داشتم و برای حل مشکلم این کارو کردن
                            //کد قبی هم موجود گذاشتم تا شاید در آینده مشکل رو متوجه شدم
                        }

                    }

                }
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_in_rec_view, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return todoList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.apply {
                timeTB.text = todoList[position].time
                titleTV.text = todoList[position].title
                descriptionTB.text = todoList[position].description
                dateTB.text = todoList[position].date
                isDoneCheckBox.isChecked = todoList[position].isDone
            }

        }


    }

