package com.example.room

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.room.users.UserActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var adapter: TaskAdapter

    private lateinit var dao:TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)
        dao =  database.taskDao()

        createBooksAdapter()
        initViews()
    }

    private fun initViews() {


        addTaskButton.setOnClickListener {
            addTask()
            titleEditText.text.clear()
        }

        toUserPageButton.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        dao.getAll().observe(this, Observer {
            adapter.contentList = it
            recyclerView.adapter = adapter
        })
    }

    private fun addTask() {


        val task = Task(titleEditText.text.toString(), false)

        thread {
            dao.insert(task)
        }
    }

    private fun createBooksAdapter() {

        adapter = TaskAdapter { task ->
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("taskId", task.id)
            startActivity(intent)
        }
    }


}
