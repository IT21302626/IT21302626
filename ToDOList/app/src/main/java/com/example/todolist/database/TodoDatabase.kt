package com.example.todolist.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.database.daos.TodoDao

abstract class TodoDatabase: RoomDatabase(){
    abstract fun getTodoDao(): TodoDao
    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getInstance(context: Context):TodoDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
