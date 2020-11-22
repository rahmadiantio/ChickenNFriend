package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.database.UserDatabase
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<UserEntity>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application)!!.userDao()
        repository =
            UserRepository(
                userDao
            )
        readAllData = repository.readAllData
    }

    fun tambahUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.tambahUser(user)
        }
    }
}