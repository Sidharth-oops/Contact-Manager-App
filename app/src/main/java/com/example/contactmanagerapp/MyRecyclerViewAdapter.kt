package com.example.contactmanagerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanagerapp.databinding.CardItemBinding

class MyRecyclerViewAdapter(
    private val usersList: List<User>,
    private val clickListener: (User) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return  usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(user = usersList[position], clickListener)
    }

}

class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener: (User) -> Unit) {
        binding.nameTextView.text = user.name
        binding.emailTextView.text=user.email
        binding.listItemLayout.setOnClickListener {
            clickListener(user)
        }
    }

}