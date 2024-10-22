package com.runitrut.ruts_recipe_app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // private val _categorieState that holds data, from data class RecipeState
    private val _categorieState = mutableStateOf(RecipeState())

    // val categegoriesState that has access to private val _categorieState
    val categegoriesState: State<RecipeState> = _categorieState

    // init block that initializes the fetchCategories function
    init {
        fetchCategories()
    }

    // fetchCategories function that fetches the categories from the API
    private fun fetchCategories(){
        // launches coroutines
         viewModelScope.launch {
             // try/catch block inside the coroutine
             try {
                 // calls the retrofit object that has access to the API CategoriesResponse
                 val response = recipeService.getCategories()
                 // store the API CatergoriesResponse in the private _categorieState property
                 _categorieState.value = _categorieState.value.copy(
                     loading = false,
                     list = response.categories,
                     error = null
                 )
                 // catch block that handles exceptions
             } catch (e: Exception){
                 // loading error for RecipeState data class
                 _categorieState.value = _categorieState.value.copy(
                     loading = false,
                     error = "Error fetching Categories ${e.message}"
                 )
             }
         }
    }
}
// data Model class that holds the data structure/parameters of the API response
data class RecipeState(
    val loading: Boolean = false,
    val list: List<Category> = emptyList(),
    val error: String? = null
)