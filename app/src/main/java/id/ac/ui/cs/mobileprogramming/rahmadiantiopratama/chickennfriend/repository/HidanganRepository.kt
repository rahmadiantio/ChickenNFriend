package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.dao.HidanganDAO
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity

class HidanganRepository(private val hidanganDao: HidanganDAO){
    val readAllData: LiveData<List<HidanganEntity>> = hidanganDao.getAllHidangan()

    suspend fun tambahHidangan(hidanganEntity: HidanganEntity){
        hidanganDao.tambahHidangan(hidanganEntity)
    }

    suspend fun updateHidangan(hidanganEntity: HidanganEntity){
        hidanganDao.updateHidangan(hidanganEntity)
    }

    suspend fun hapusHidangan(hidanganEntity: HidanganEntity){
        hidanganDao.hapusHidangan(hidanganEntity)
    }
}