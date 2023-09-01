package com.example.contactmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        //Room Database
        val dao=UserDatabase.getInstance(application).userDao
        val repository=Repository(dao)
        val factory=UserViewModelFactory(repository)
        userViewModel=ViewModelProvider(this,factory).get(UserViewModel::class.java)
        binding.userViewModel=userViewModel
         binding.lifecycleOwner=this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun DisplayUserList() {
      userViewModel.users.observe(this,{
          binding.recyclerView.adapter=MyRecyclerViewAdapter(

              it,{selectedItem:User->listItemClicked(selectedItem)}
          )
      })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this,"Selected item is ${selectedItem.name}",Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(selectedItem)

    }
}