package com.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.todoapp.R
import com.todoapp.databinding.FragmentAddTaskBinding
import com.todoapp.model.ToDo
import com.todoapp.utils.Picker
import com.todoapp.utils.fulldate
import com.todoapp.utils.hour
import com.todoapp.utils.minet


class AddTask : Fragment() {
   private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateInput.setOnClickListener{
            Picker(parentFragmentManager,binding.dateInput)
        }
        binding.addtask.setOnClickListener {
            val newToDo = ToDo(
                binding.titelInputEditText.editText?.text.toString(),
                binding.descriptionInputET.editText?.text.toString(),
                "$hour:$minet",
                fulldate,
                false
            )
            todoList.add(newToDo)
            Navigation.findNavController(binding.addtask).navigate(R.id.action_addTask_to_currentToDos)

        }
    }
}