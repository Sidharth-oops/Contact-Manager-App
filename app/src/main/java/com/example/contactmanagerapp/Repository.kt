package com.example.contactmanagerapp

class Repository(private val userDao: UserDao) {
    val users = userDao.getAllUserInDataBase()
    suspend fun addUserToTheRoom(user: User) :Long {
         return userDao.addUser(user)
    }

    suspend fun deleteUserFromTheRoom(user: User) {
        return userDao.deleteUser(user)
    }

    suspend fun updateUserInTheRoom(user: User) {
       return  userDao.updateUser(user)
    }
    suspend fun deleteAll(){
        return userDao.deleteAll()
    }
}