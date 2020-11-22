package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "komentar_table")
data class KomentarEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val komentar: String,
    val status: Boolean
){}