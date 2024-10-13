package com.khyamadhikari.comp304.lab2.ex1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateTaskScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due Date") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (title.isNotEmpty() && description.isNotEmpty() && dueDate.isNotEmpty()) {
                // Create a new Task and add it to the ViewModel
                val newTask = Task(
                    id = viewModel.tasks.value.size + 1,  // Increment task ID
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    isCompleted = false  // Newly created tasks are marked as pending by default
                )
                viewModel.addTask(newTask)  // Add the task to the ViewModel
                navController.popBackStack()  // Navigate back to HomeScreen
            }
        }) {
            Text("Save Task")
        }
    }
}
