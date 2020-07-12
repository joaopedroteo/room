package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert
    fun insert(task: Task): Long

    @Insert
    fun insertAll(tasks: List<Task>): List<Long>

    @Query("SELECT * FROM Task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :taskId")
    fun getTask(taskId:Int): LiveData<Task>

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM Task WHERE id = :taskId")
    fun delete(taskId:Int)

}