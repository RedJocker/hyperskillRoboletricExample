package org.hyperskill.projectname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoListRecyclerViewAdapter(private val todoRepository: TodoRepository): RecyclerView.Adapter<TodoListRecyclerViewAdapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoRepository.getTodo(position)

        println("onBindViewHolder position: $position todo: $todo")

        holder.itemView.findViewById<TextView>(R.id.listItemTvTitle).text = todo.title
        holder.itemView.findViewById<TextView>(R.id.listItemTvDescription).text = todo.description
    }

    override fun getItemCount(): Int {
        return todoRepository.size()
    }


    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}