package com.example.todolist.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Todo {
    @Insert
    suspend fun insertTodo(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
    @Query("SELECT * From Todo")
    fun getAllUsers(): List<Todo>
}