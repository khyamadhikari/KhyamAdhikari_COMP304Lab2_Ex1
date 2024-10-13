package com.khyamadhikari.comp304.lab2.ex1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun EditTaskScreen(
    navController: NavController,
    taskId: Int,
    viewModel: TaskViewModel = viewModel()  // Accessing TaskViewModel
) {
    // Fetch the task by its ID
    val task = viewModel.getTaskById(taskId) ?: return  // If task is not found, return early

    // State variables to hold the current values for the task's fields
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var isCompleted by remember { mutableStateOf(task.isCompleted) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Checkbox to update the completion status
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { isCompleted = it }
            )
            Text(text = if (isCompleted) "Completed" else "Pending")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save button to save changes and update the task
        Button(onClick = {
            val updatedTask = task.copy(
                title = title,
                description = description,
                dueDate = dueDate,
                isCompleted = isCompleted  // Update the completion status
            )

            // Call the updateTask() method to save the updated task in the ViewModel
            viewModel.updateTask(updatedTask)

            // Navigate back to the home screen
            navController.navigateUp()
        }) {
            Text("Save Changes")
        }
    }
}
