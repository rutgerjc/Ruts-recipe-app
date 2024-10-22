package com.runitrut.ruts_recipe_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
// starting screen for the app
fun RecipeApp(navController: NavHostController)
{
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categegoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        // this composable is responsible for the recipe on the RecipeScreen
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewState = viewState, navigateToDetails = {
                // This part is responsible for passing the data to the detail screen.
                // It utilizes the savedStateHandle, which is a component of the navigation framework.
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            })
        }
        // this composable is responsible for the detail in the DetailScreen.
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                // If the category is null, it will return an empty category.
                ?: Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }
}