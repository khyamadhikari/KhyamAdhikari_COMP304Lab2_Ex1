package com.khyamadhikari.comp304.lab2.ex1

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    // MutableStateFlow to manage task updates
    private val _tasks = MutableStateFlow(
        listOf(
            Task(1, "Complete Lab 2", "Finish the Android lab assignment for COMP304", "2024-10-12", false),
            Task(2, "Buy Groceries", "Get fruits, vegetables, and milk from the store", "2024-10-13", true),

        )
    )
    val tasks: StateFlow<List<Task>> = _tasks

    // Function to add a new task to the list
    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task  // Create a new list by appending the task
        Log.d("TaskViewModel", "Added Task: ${task.title}, Total tasks: ${_tasks.value.size}")
    }

    // Function to update an existing task in the list
    fun updateTask(updatedTask: Task) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == updatedTask.id) updatedTask else task
        }
        Log.d("TaskViewModel", "Updated Task: ${updatedTask.title}")
    }

    // Function to get a task by ID
    fun getTaskById(taskId: Int): Task? {
        return _tasks.value.find { it.id == taskId }
    }
}
