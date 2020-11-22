package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hidangan_table")
data class HidanganEntity(
   @PrimaryKey(autoGenerate = true)
   val id: Int,
   val nama: String,
   val foto: String?,
   val saus: String?,
   val minuman: String
) {}