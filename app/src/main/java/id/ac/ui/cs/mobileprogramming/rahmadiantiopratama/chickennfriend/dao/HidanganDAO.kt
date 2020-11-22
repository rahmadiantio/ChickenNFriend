package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity

@Dao
interface HidanganDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahHidangan(hidanganEntity: HidanganEntity)

    @Update
    suspend fun updateHidangan(hidanganEntity: HidanganEntity)

    @Delete
    suspend fun hapusHidangan(hidanganEntity: HidanganEntity)

    @Query("SELECT * FROM hidangan_table ORDER BY id ASC")
    fun getAllHidangan() : LiveData<List<HidanganEntity>>
}