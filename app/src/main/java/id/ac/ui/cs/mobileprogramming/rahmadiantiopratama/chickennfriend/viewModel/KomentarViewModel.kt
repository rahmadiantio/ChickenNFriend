package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.database.KomentarDatabase
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.repository.KomentarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KomentarViewModel(application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<KomentarEntity>>
    private val repository: KomentarRepository

    init {
        val komentarDao = KomentarDatabase.getDatabase(application)!!.komentarDao()
        repository =
            KomentarRepository(
                komentarDao
            )
        readAllData = repository.readAllData
    }

    fun tambahKomentar(komentarEntity: KomentarEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.tambahKomentar(komentarEntity)
        }
    }

    fun updateKomentar(komentarEntity: KomentarEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateKomentar(komentarEntity)
        }
    }
}