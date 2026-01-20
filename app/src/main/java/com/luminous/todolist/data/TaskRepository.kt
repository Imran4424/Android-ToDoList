package com.luminous.todolist.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
        fun observeTasks(completed: Boolean): Flow<List<Task>>
        suspend fun addTask(title: String)
        suspend fun setTaskCompleted(id: Long, completed: Boolean)
        suspend fun deleteTask(id: Long)
        suspend fun updateTaskTitle(id: Long, title: String)
}
