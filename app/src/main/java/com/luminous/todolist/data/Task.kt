package com.luminous.todolist.data

data class Task(
        val id: Long,
        val title: String,
        val isCompleted: Boolean,
        val createdAt: Long,
        val completedAt: Long?
)
