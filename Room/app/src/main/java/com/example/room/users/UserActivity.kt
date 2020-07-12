package com.example.room.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.AppDatabase
import com.example.room.R
import com.example.room.TaskAdapter
import com.example.room.TaskDAO

class UserActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userDao = AppDatabase.getInstance(this).userDao()

    }
}
