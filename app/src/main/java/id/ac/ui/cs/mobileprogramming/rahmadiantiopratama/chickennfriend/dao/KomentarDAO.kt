package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity

@Dao
interface KomentarDAO{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahKomentar(komentarEntity: KomentarEntity)

    @Update
    suspend fun updateKomentar(komentarEntity: KomentarEntity)

    @Query("SELECT * FROM komentar_table ORDER BY id ASC")
    fun getAllKomentar(): LiveData<List<KomentarEntity>>
}