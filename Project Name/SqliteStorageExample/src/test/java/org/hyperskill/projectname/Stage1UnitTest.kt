package org.hyperskill.projectname

import android.content.ContentValues
import android.database.sqlite.SQLiteException
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hyperskill.projectname.internals.AbstractUnitTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class Stage1UnitTest : AbstractUnitTest<MainActivity>(MainActivity::class.java){


    private val etTitle: EditText by lazy {
        activity.findViewByString("etTitle")
    }

    private val etDescription: EditText by lazy {
        activity.findViewByString("etDescription")
    }

    private val btnSave: Button by lazy {
        activity.findViewByString("btnSave")
    }

    private val rvList: RecyclerView by lazy {
        activity.findViewByString("rvList")
    }

    val expectedList = listOf(
        "some todo title" to "some todo description",
        "another title" to "another description",
        "yet another title" to "yet another description"
    )


    @Test
    fun testDataSaved() {


        testActivity {
            activity

            expectedList.forEach {
                etTitle.setText(it.first)
                etDescription.setText(it.second)
                btnSave.clickAndRun()
            }

            TestDatabaseFactory().readableDatabase.use { database ->

                println(database.isOpen)
                println(database.path)
                database.query("todos", null,
                    null, null, null, null, null).use { cursor ->

                    assertEquals("wrong size", expectedList.size, cursor.count)

                    val titleColumnIndex = cursor.getColumnIndex("title")
                    val descriptionColumnIndex = cursor.getColumnIndex("description")
                    assertTrue("title column was not found", titleColumnIndex >= 0)
                    assertTrue("description column was not found", descriptionColumnIndex >= 0)

                    var i = 0
                    while(cursor.moveToNext()) {
                        val (expectedTitle, expectedDescription) = expectedList[i]
                        val actualTitle = cursor.getString(titleColumnIndex)
                        val actualDescription = cursor.getString(descriptionColumnIndex)

                        assertEquals("wrong title", expectedTitle, actualTitle)
                        assertEquals("wrong description", expectedDescription, actualDescription)

                        i++
                    }
                }
            }
        }
    }

    @Test
    fun testDataRestored(){
        TestDatabaseFactory().writableDatabase.use { database ->
            database.execSQL("CREATE TABLE IF NOT EXISTS todos(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT);")
            database.beginTransaction()
            try {
                expectedList.forEach {
                    ContentValues().apply {
                        put("title", it.first)
                        put("description", it.second)
                        database.insert("todos", null, this)
                    }
                }
                database.setTransactionSuccessful()
            } catch (ex: SQLiteException) {
                ex.printStackTrace()
                fail(ex.stackTraceToString())
            } catch (ex: IllegalStateException) {
                ex.printStackTrace()
                fail(ex.stackTraceToString())
            } finally {
                database.endTransaction()
            }
        }

        testActivity {
            rvList

            rvList.assertListItems(expectedList) { itemViewSupplier, index, item ->
                val itemView = itemViewSupplier()
                val listItemTvTitle = itemView.findViewByString<TextView>("listItemTvTitle")
                val listItemTvDescription = itemView.findViewByString<TextView>("listItemTvDescription")

                val expectedTitle = item.first
                val expectedDescription = item.second

                val actualTitle = listItemTvTitle.text.toString()
                val actualDescription = listItemTvDescription.text.toString()

                assertEquals("wrong title", expectedTitle, actualTitle)
                assertEquals("wrong description", expectedDescription, actualDescription)
            }
        }

    }
}