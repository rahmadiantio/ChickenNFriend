package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao.UserDAO
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity

class UserRepository(private val userDAO: UserDAO) {
    val readAllData: LiveData<List<UserEntity>> = userDAO.getAllUser()
    suspend fun tambahUser(user: UserEntity){
        userDAO.tambahUser(user)
    }

    suspend fun findUserByUsername(username: String): List<UserEntity>{
        return userDAO.findUserByUsername(username)
    }
}