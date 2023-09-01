package com.example.contactmanagerapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
@Insert
suspend fun addUser(user:User):Long
@Update
suspend fun updateUser(user:User)
@Delete
suspend fun deleteUser(user: User)
@Query("DELETE from user")
suspend fun deleteAll()
   @Query("SELECT * from user")
   fun getAllUserInDataBase():LiveData<List<User>>
}