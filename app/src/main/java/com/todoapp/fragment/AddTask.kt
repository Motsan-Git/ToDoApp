package com.todoapp.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import android.media.MediaPlayer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.todoapp.R
import com.todoapp.databinding.FragmentAddTaskBinding
import com.todoapp.model.ToDo
import com.todoapp.notifiction.Notif
import com.todoapp.utils.Picker
import com.todoapp.utils.fulldate
import com.todoapp.utils.hour
import com.todoapp.utils.minute

import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.launch


class AddTask : Fragment() {
    private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateAITB.setOnClickListener {
            Picker(parentFragmentManager, binding.dateAITB)
        }
        binding.addtaskBTN.setOnClickListener {
            val newToDo = ToDo(
                binding.titelITB.editText?.text.toString(),
                binding.descrITB.editText?.text.toString(),
                "$hour:$minute",
                fulldate,
                false

            )
            lifecycleScope.launch {
                requireContext().dataStore.updateData {
                    it.copy(
                        it.todoList.mutate {
                            it.add(newToDo)
                        }
                    )
                }
            }
            var mediaPlayer: MediaPlayer
            val soundResId = R.raw.sound  // شناسه منبع صدا
            mediaPlayer = MediaPlayer.create(requireContext(), soundResId)
            mediaPlayer.start()
            Notif(newToDo.title,newToDo.description,requireContext(),newToDo.hashCode().toString(),newToDo)
            Navigation.findNavController(binding.addtaskBTN)
                .navigate(R.id.action_addTask_to_currentToDos)
        }
    }
}