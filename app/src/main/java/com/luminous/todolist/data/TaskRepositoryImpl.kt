package com.luminous.todolist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
        override fun observeTasks(completed: Boolean): Flow<List<Task>> {
                return taskDao.observeByCompletion(completed).map { entities -> entities.map { it.toTask() } }
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


// Extension function of TaskEntity to convert TaskEntity to Task
private fun TaskEntity.toTask(): Task {
        return Task(
                id = id,
                title = title,
                isCompleted = isCompleted,
                createdAt = createdAt,
                completedAt = completedAt
        )
}