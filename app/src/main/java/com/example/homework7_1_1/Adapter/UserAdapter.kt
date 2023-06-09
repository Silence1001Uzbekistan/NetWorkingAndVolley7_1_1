package com.example.networkingpractise.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7_1_1.Models.UserItem
import com.example.homework7_1_1.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class UserAdapter(var list: List<UserItem>) : RecyclerView.Adapter<UserAdapter.vh>() {

    inner class vh(var itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {

        fun onBind(user: UserItem) {

            itemUserBinding.tv1.text = user.login

            Picasso.get().load(user.avatar_url).into(itemUserBinding.imageId)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {

        return vh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: vh, position: Int) {

        holder.onBind(list[position])

    }


}