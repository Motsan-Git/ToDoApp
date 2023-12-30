package com.todoapp.db

import com.todoapp.model.ToDo
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class TodoListHolder(
    @Serializable(TodoListSeryalayzer::class)
    val todoList : PersistentList<ToDo> = persistentListOf()

)
