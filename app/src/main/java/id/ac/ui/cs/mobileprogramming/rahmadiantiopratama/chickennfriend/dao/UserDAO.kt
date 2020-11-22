package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity

@Dao
interface UserDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahUser(user: UserEntity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_table WHERE username LIKE :username")
    fun findUserByUsername(username: String) : List<UserEntity>
}