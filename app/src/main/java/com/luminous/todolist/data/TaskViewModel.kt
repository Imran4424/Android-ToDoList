package com.luminous.todolist.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao): ViewModel() {
        private val repo: TaskRepository = TaskRepositoryImpl(taskDao)

        // Active (not completed) tasks
        val activeTasks: StateFlow<List<Task>> =
                repo.observeTasks(completed = false)
                        .stateIn(
                                scope = viewModelScope,
                                started = SharingStarted.WhileSubscribed(5000),
                                initialValue = emptyList()
                        )

        // Completed tasks
        val completedTasks: StateFlow<List<Task>> =
                repo.observeTasks(completed = true)
                        .stateIn(
                                scope = viewModelScope,
                                started = SharingStarted.WhileSubscribed(5000),
                                initialValue = emptyList()
                        )

        fun addTask(title: String) = viewModelScope.launch {
                val trimmed = title.trim()
                if (trimmed.isNotEmpty()) {
                        repo.addTask(trimmed)
                }
        }

        fun markDone(taskId: Long) = viewModelScope.launch {
                repo.setTaskCompleted(id = taskId, completed = true)
        }

        fun undoDone(taskId: Long) = viewModelScope.launch {
                repo.setTaskCompleted(id = taskId, completed = false)
        }

        fun deleteTask(taskId: Long) = viewModelScope.launch {
                repo.deleteTask(taskId)
        }

        fun updateTaskTitle(taskId: Long, newTitle: String) = viewModelScope.launch {
                val trimmed = newTitle.trim()

                if (trimmed.isNotEmpty()) {
                        repo.updateTaskTitle(id = taskId, title = trimmed)
                }
        }

        class Factory(private val taskDao: TaskDao): ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TaskViewModel(taskDao) as T
                }
        }
}