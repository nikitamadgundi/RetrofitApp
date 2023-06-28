package com.example.retrofitapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.DataModel.NewUserResponse
import com.example.retrofitapp.DataModel.User


import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.UserViewBinding
import com.squareup.picasso.Picasso
import java.net.UnknownServiceException

class AdatperUser(val UserList: ArrayList<User>) : RecyclerView.Adapter<AdatperUser.UserViewHolder>() {

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: UserViewBinding

        init {
            binding = UserViewBinding.bind(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_view, null))
    }

    override fun getItemCount() = UserList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = UserList[position]
         holder.binding.txtEmail.text = user.email
         holder.binding.txtName.text = "${user.first_name} ${user.first_name}"
        holder.binding.txtMoreData.text = user.toString()


        holder.binding.btnViewMore.setOnClickListener(View.OnClickListener { view ->
            if (holder.binding.llViewMore.isVisible) {
                holder.binding.llViewMore.isGone = true
            } else {
                holder.binding.llViewMore.isVisible = true
            }
        })
        if (user.avatar.toString() != "") {
            Picasso.get()
                .load(user.avatar)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.binding.imgUser)
        }

    }
}