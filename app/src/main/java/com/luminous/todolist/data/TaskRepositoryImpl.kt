package com.luminous.todolist.data

import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
        override fun observeTasks(completed: Boolean): Flow<List<Task>> {
                TODO("Not yet implemented")
        }

        override suspend fun addTask(title: String) {
                TODO("Not yet implemented")
        }

        override suspend fun setTaskCompleted(id: Long, completed: Boolean) {
                TODO("Not yet implemented")
        }

        override suspend fun deleteTask(id: Long) {
                TODO("Not yet implemented")
        }

        override suspend fun updateTaskTitle(id: Long, title: String) {
                TODO("Not yet implemented")
        }
}