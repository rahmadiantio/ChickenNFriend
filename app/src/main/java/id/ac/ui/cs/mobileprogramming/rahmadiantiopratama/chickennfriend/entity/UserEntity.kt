package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nama: String,
    val email: String,
    val username: String,
    val password: String
){}