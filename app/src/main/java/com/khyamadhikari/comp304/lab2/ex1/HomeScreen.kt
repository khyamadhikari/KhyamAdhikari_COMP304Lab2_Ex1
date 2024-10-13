package com.khyamadhikari.comp304.lab2.ex1

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    // Collect the task list as state from TaskViewModel
    val taskList by viewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("createTask")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) { contentPadding ->
        if (taskList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "No tasks available", modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(modifier = Modifier.padding(contentPadding)) {
                items(taskList) { task ->
                    // Ensure task and onClick are passed to TaskItem
                    TaskItem(
                        task = task,
                        onClick = {
                            navController.navigate("editTask/${task.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),  // Utilize the onClick parameter here
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                // Utilize the task parameter here to display its title and completion status
                Text(text = task.title)
                Text(text = if (task.isCompleted) "Completed" else "Pending")
            }
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { /* Checkbox logic can be handled in EditTaskScreen */ }
            )
        }
    }
}
