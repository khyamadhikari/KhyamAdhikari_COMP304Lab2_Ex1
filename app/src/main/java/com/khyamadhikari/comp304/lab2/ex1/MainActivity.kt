package com.khyamadhikari.comp304.lab2.ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable("createTask") { CreateTaskScreen(navController) }
                composable("editTask/{taskId}") { backStackEntry ->
                    val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
                    EditTaskScreen(navController, taskId)
                }
            }
        }
    }
}
