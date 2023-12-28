package com.todoapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.todoapp.R
import com.todoapp.adaptor.ToDoAdaptor
import com.todoapp.databinding.FragmentAddTaskBinding
import com.todoapp.databinding.FragmentCurrentToDosBinding
import com.todoapp.model.ToDo

val todoList = mutableListOf<ToDo>()

class CurrentToDos : Fragment() {
    private lateinit var binding: FragmentCurrentToDosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentToDosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addTaskBTN.setOnClickListener {
            Navigation.findNavController(binding.addTaskBTN)
                .navigate(R.id.action_currentToDos_to_addTask)
        }
        initrecView()
    }

    override fun onResume() {
        super.onResume()
        initrecView()

    }

    private fun initrecView() {
        val adaptor =
            ToDoAdaptor(
                todoList,
                requireContext()
            )
        binding.recView.adapter = adaptor
        binding.recView.layoutManager = LinearLayoutManager(requireContext())

    }
}

