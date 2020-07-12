package com.example.room.users

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Insert
    fun insertAllUsers(users: List<User>): List<Long>

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUser(userId:Int): LiveData<User>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM Task WHERE id = :userId")
    fun deleteUser(userId:Int)

}