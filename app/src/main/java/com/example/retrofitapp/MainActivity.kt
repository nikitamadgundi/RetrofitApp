package com.example.retrofitapp

import UserListResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.Adapter.AdatperUser
import com.example.retrofitapp.ApiCalls.ApiService
import com.example.retrofitapp.DataModel.NewUserRequest
import com.example.retrofitapp.DataModel.User
import com.example.retrofitapp.DataModel.UserResponse
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adatperUser: AdatperUser
    private var UserList: ArrayList<User> = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        getAllUser()
       // getUserById()
        getNewUser()

    }

    private fun getAllUser() {
        try {
            lifecycleScope.launch {
                val userListResponse: UserListResponse = ApiService.retrofit.getAllList()
                Log.e("Api Calls" , "${userListResponse.toString()}")
                UserList = userListResponse.data as ArrayList<User>
                // UserList = userListResponse.data as ArrayList<User>
                adatperUser = AdatperUser(UserList)
                recyclerView.adapter = adatperUser

                adatperUser.setOnclickListener= SetOnClickListener()

            }
        }catch (e:Exception){
            Log.e("Api calls","${e.message}")
        }

    }

    private fun getUserById() {
        try {
            lifecycleScope.launch {
                val userListResponse : UserListResponse = ApiService.retrofit.getAllList(2)
                Log.e("Api Calls", "${userListResponse.toString()}")
              // UserList = userListResponse.data as ArrayList<User>
                adatperUser = AdatperUser(UserList)
                recyclerView.adapter = adatperUser

                adatperUser.setOnclickListener= SetOnClickListener()


                // Handle the response or do further processing
            }
        } catch (e: Exception) {
            Log.e("Api calls", "${e.message}")
        }
    }


    private fun getNewUser() {

        try {
            lifecycleScope.launch {
                val newUser =
                    ApiService.retrofit.getNewUser(NewUserRequest("Nikki", "Android Developer"))
                Log.e("Api Calls", "${newUser.body().toString()}")
                // Handle the response or do further processing
            }
        } catch (e: Exception) {
            Log.e("Api calls", "${e.message}")
        }


    }

    inner class SetOnClickListener : AdatperUser.SetOnclickListener{
        override fun setOnClick(position: Int) {
            val intent  = Intent(this@MainActivity,HomeAcivity::class.java)
            startActivity(intent)
        }

    }



}



