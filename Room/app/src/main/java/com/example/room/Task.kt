package com.example.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.room.users.User

@Entity(
    foreignKeys = [(ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"]
    ))]
)
data class Task(
    @PrimaryKey(autoGenerate = true) var id:Int = 1,
    var title:String = "",
    var completed:Boolean = false,
    var userId:Int?
) {
    @Ignore
    constructor(
        title:String ="",
        completed:Boolean = false,
        userId: Int? = null
    ):this(0,title,completed, userId)
}