package com.runitrut.ruts_recipe_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// base URL for the API call
private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory
        .create())
        .build()

// Property used to create the whole API call
    val recipeService = retrofit.create(ApiService::class.java)

// endpoint for the API call
interface ApiService{
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}