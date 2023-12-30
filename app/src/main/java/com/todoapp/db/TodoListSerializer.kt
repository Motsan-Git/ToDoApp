package com.todoapp.db

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import java.io.OutputStream

class TodoListSerializer : Serializer<TodoListHolder> {
    override val defaultValue = TodoListHolder()


    override suspend fun readFrom(input: InputStream): TodoListHolder {
        return Json.decodeFromString(
            TodoListHolder.serializer(),
            input.readBytes().decodeToString()
        )
    }

    override suspend fun writeTo(holdr: TodoListHolder, output: OutputStream) {
        output.write(Json.encodeToString(TodoListHolder.serializer(), holdr).encodeToByteArray())


    }

}

