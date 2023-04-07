package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlinx.coroutines.withContext

abstract class TodoAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {\

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cbTodo: CheckBox
        val ivDelete: ImageView

        init {
            cbTodo = view.findViewById(R.id.cbTodo)
            ivDelete = view.findViewById(R.id.ivDelete)
        }

        fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item,parent,false)
            return ViewHolder(view)
        }

        fun getItemCount(): Int {
            return 1
        }

        override fun onBindViewHolder (holder: ViewHolder, position: Int) {
            val data = null
            holder.cbTodo.text = data[position].item
            if (holder.cbTodo.isChecked){
                val repository = TodoRepository (TodoDatabase.getInstance(context))
                val Coroutine
                Coroutine Scope (Dispatchers. 10). Launch this: CoroutineScope repository.delete(data[position])
                val data = repository.getAllTodos ()
                withContext (Dispatchers.Main) { this: CoroutineScope setData(data, context)
                }
            }
        }else{
            val text
            Toast.makeText(context, text: "Cannot delete unchecked Todo items", Toast.LENGTH_LONG).show()
        }
    }
