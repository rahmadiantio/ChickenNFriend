package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.database.HidanganDatabase
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository.HidanganRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HidanganViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<HidanganEntity>>
    private val repository: HidanganRepository

    init {
        val hidanganDao = HidanganDatabase.getDatabase(application)!!.hidanganDao()
        repository = HidanganRepository(hidanganDao)
        readAllData = repository.readAllData
    }

    fun tambahHidangan(hidanganEntity: HidanganEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.tambahHidangan(hidanganEntity)
        }
    }

    fun updateHidangan(hidanganEntity: HidanganEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHidangan(hidanganEntity)
        }
    }

    fun hapusHidangan(hidanganEntity: HidanganEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.hapusHidangan(hidanganEntity)
        }
    }
}