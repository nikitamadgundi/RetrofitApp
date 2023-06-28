package com.example.retrofitapp.ApiCalls


import UserListResponse
import com.example.retrofitapp.DataModel.NewUserRequest
import com.example.retrofitapp.DataModel.UserResponse


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService  {


    @GET("users")
    suspend fun getAllList() : UserListResponse

    @GET("users/{id}")
    suspend fun getAllList(@Path("id")UserId : Int) : UserListResponse

    @POST("users")
    suspend fun getNewUser(@Body newUserRequest: NewUserRequest) : Response<NewUserRequest>


    companion object{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService :: class.java)
    }
}