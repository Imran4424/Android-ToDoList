package com.luminous.todolist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
        @Query(
                """
                        SELECT * FROM tasks
                        WHERE isCompleted = :completed
                        ORDER BY 
                            CASE WHEN :completed = 1 THEN completedAt END DESC
                """
        )
        fun observeByCompletion(completed: Boolean): Flow<List<TaskEntity>>

        @Insert
        suspend fun insert(task: TaskEntity): Long

        @Query("UPDATE tasks SET isCompleted = :isCompleted, completedAt = :completedAt WHERE id = :id")
        suspend fun setCompleted(id: Long, isCompleted: Boolean, completedAt: Long?)

        @Query("DELETE FROM tasks WHERE id = :id")
        suspend fun deleteById(id: Long)
}