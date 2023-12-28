package com.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController


class CurrentToDos : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_to_dos, container, false)
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
        val textView =view.findViewById<TextView>(R.id.textView1)
        textView.setOnClickListener {
            findNavController(textView).navigate(R.id.action_currentToDos_to_addTask)
            Log.d("What","This is a ")
        }
    }
}

