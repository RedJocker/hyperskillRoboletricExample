package org.hyperskill.projectname

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TodoRepository(this, "playgroundDatabase.db")
        val todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(repository)

        findViewById<RecyclerView>(R.id.rvList).apply {
            adapter = todoListRecyclerViewAdapter
        }

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            if(title.isNotBlank() && description.isNotBlank()) {
                val pos = repository.addTodo(Todo(null, title, description))
                if(pos >= 0) {
                    todoListRecyclerViewAdapter.notifyItemInserted(pos)
                }
            }
        }
    }
}
