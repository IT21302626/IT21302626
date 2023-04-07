package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.TodoDatabase
import com.example.todolist.database.daos.Todo
import com.example.todolist.database.entities.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlinx.coroutines.launch
import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import com.example.todolist.adapters.TodoAdapter as TodoAdapter1

class MainActivity: AppCompatActivity() { 3 override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val repository = TodoRepository (TodoDatabase.getInstance( context: this))
    val recyclerView: RecyclerView = findViewById(R.id.rvToDoList)
    val ui= this
    val adapter = TodoAdapter1()

    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(ui)
    CoroutineScope (Dispatchers. 10). launch this: CoroutineScope
    val data = repository.getAllTodos ()
    adapter.setData(data, ui)

}
    fun displayDialog(repository: TodoRepository, adapter: TodoAdapter1) {

        // Create a new instance of AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        builder.setTitle("Enter New Todo item:")
        builder.setMessage("Enter the todo item below:")

        // Create an EditText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->

            // Get the input text and display a Toast message
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Todo(item))

                val data = repository.getAllTodos()
                runOnUiThread{
                    adapter.setData(data,this@MainActivity)
                }
            }
        }

        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        // Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()

    }
}