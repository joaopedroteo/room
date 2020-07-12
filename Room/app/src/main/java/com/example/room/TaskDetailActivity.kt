package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_task_detail.*
import kotlin.concurrent.thread

class TaskDetailActivity : AppCompatActivity() {


    private lateinit var taskDao:TaskDAO

    private lateinit var task:Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        taskDao = AppDatabase.getInstance(this).taskDao()

        initViews()
        createObserver()

        var opcoes = arrayListOf("primerio" ,"segundo", "terceiro")

        var adapter:ArrayAdapter<String> = ArrayAdapter(applicationContext, R.layout.item_spinner, opcoes)
        detailUserSpinner.adapter = adapter
    }

    private fun initViews() {
        detailDeleteButton.setOnClickListener {
            finish()
            thread { taskDao.delete(task) }
        }

        detailCompletedCheckBox.setOnCheckedChangeListener{_, isChecked ->
            if(::task.isInitialized) {
                task.let {
                    it.completed = isChecked
                    thread { taskDao.update(it) }
                }
            }

        }
    }

    private fun createObserver() {
        val taskId = intent?.getIntExtra("taskId", 0)
        taskDao.getTask(taskId ?: -1).observe(this, Observer {
            if (it == null) {
                Toast.makeText(applicationContext, "Nao encontrado", Toast.LENGTH_SHORT).show()
                finish()
                return@Observer
            }
            detailTitleTextView.text = it.title
            detailCompletedTextView.text = if (it.completed) "Concluído" else "Pendente"
            detailCompletedCheckBox.isChecked = it.completed
            task = it

        })
    }
}
