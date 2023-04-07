package com.example.todolist.database.entities.repositories

import com.example.todolist.database.TodoDatabase
import com.example.todolist.database.daos.Todo

class TodoRepository(
    private val db: TodoDatabase
) {
    suspend fun insert(todo: Todo) = db.getTodoDao().insertTodo(todo)
    suspend fun delete(todo: Todo) = db.getTodoDao().delete(todo)
    fun getAllTodos() =db.getTodoDao().getAllTodos()
}