package org.hyperskill.projectname

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase


class TodoRepository(val context: Context, val databasePath: String) {

    private val memo = mutableListOf<Todo>()

    init {
        getDatabase(databasePath)?.use { database ->
            database.execSQL("CREATE TABLE IF NOT EXISTS todos(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT);")
            memo.addAll(database.initMemo())
        } ?: throw Exception("database was null")
    }

    fun addTodo(todo: Todo): Int {
        getDatabase(databasePath)?.use { database ->

            val values = ContentValues()
            values.put("title", todo.title)
            values.put("description", todo.description)

            val rowId = database.insert("todos", null, values)

            if(rowId >= 0) {
                val todoAdded = Todo(rowId, todo.title, todo.description)
                memo.add(todoAdded)
                println("todo added $todoAdded")
                return memo.size - 1
            } else  {
                println("failed to add todo $todo")
                return -1
            }

        } ?: throw Exception("database was null")
    }

    fun getTodo(pos: Int): Todo {
        return memo[pos]
    }

    fun size(): Int {
        return memo.size
    }

    private fun SQLiteDatabase.initMemo(): List<Todo> {

        val cursor = this.query("todos", null, null, null, null, null, null)

        return generateSequence {
            cursor.moveToNext().let { hasNext ->
                if (hasNext) {
                    val id = cursor.getLong(cursor.getColumnIndex("id"))
                    val title = cursor.getString(cursor.getColumnIndex("title"))
                    val description = cursor.getString(cursor.getColumnIndex("description"))
                    val todo = Todo(id, title, description)
                    todo
                } else {
                    cursor.close()
                    null
                }
            }
        }.toList()
    }

    private fun getDatabase(databasePath: String): SQLiteDatabase? {
        return context.openOrCreateDatabase(databasePath, MODE_PRIVATE, null)
    }
}