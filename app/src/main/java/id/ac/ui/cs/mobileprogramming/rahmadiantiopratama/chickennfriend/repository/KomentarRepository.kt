package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao.KomentarDAO
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity

class KomentarRepository(private val komentarDao: KomentarDAO){
    val readAllData: LiveData<List<KomentarEntity>> = komentarDao.getAllKomentar()

    suspend fun tambahKomentar(komentarEntity: KomentarEntity){
        komentarDao.tambahKomentar(komentarEntity)
    }

    suspend fun updateKomentar(komentarEntity: KomentarEntity){
        komentarDao.updateKomentar(komentarEntity)
    }
}