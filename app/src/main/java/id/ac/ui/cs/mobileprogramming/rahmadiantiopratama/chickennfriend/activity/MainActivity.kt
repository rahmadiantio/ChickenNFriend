package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment.LoginFragment
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment.HidanganListFragment
import java.io.File
import java.util.*

private const val kodeRequest = 80
private const val fileFoto = "photo_"
private lateinit var foto: File

class MainActivity : AppCompatActivity() {
    var userLogin: UserEntity? = null
    var fotoDiambil: Bitmap? = null
    var bahasa: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadBahas()
        setContentView(R.layout.activity_main)
        loadFragment(LoginFragment.newInstance(), false)
    }

    fun loadFragment(fragment: Fragment, back: Boolean){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        if(!back){
            transaction.disallowAddToBackStack()
        }
        else{
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    fun login(user: UserEntity?){
        Log.i("masuk", user.toString())
        if(user == null){
            Toast.makeText(
                this@MainActivity, "Gagal!",
                Toast.LENGTH_SHORT
            ).show()
        }
        else{
            Toast.makeText(
                this@MainActivity, "Sukses!",
                Toast.LENGTH_SHORT
            ).show()
            userLogin = user
            loadFragment(HidanganListFragment.newInstance(), false)
        }
    }

    fun passHidangan(fragment: Fragment, hidanganEntity: HidanganEntity){
        val bundle = Bundle()
        bundle.putInt("id_hidangan", hidanganEntity.id)
        bundle.putString("nama_hidangan", hidanganEntity.nama)
        bundle.putString("foto_hidangan", hidanganEntity.foto)
        bundle.putString("saus_hidangan", hidanganEntity.saus)
        bundle.putString("minuman", hidanganEntity.minuman)
        if(hidanganEntity.foto != null){
            setPictureFile(hidanganEntity.foto)
            fotoDiambil = BitmapFactory.decodeFile(foto.absolutePath)
        }
        fragment.arguments = bundle
        loadFragment(fragment, true)
    }

    fun setPictureFile(fotoPath: String){
        foto = File(fotoPath)
    }

    fun ambilFoto(){
        val intentFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        foto = getFoto(fileFoto)

        val fileProvider = FileProvider.getUriForFile(this, "id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.fileprovider", foto)
        intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        startActivityForResult(intentFoto, kodeRequest)
    }

    private fun getFoto(namaFile: String): File{
        val direktori = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(namaFile, ".jpg", direktori)
    }

    fun getFileFoto(): File{
        return foto
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == kodeRequest && resultCode == Activity.RESULT_OK){
            val ambilFoto =BitmapFactory.decodeFile(foto.absolutePath)
            fotoDiambil = ambilFoto
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun gantiBahasa(bahasa: String){
        setBahassa(bahasa)
        recreate()
    }

    fun setBahassa(bahassa: String){
        val lokal = Locale(bahassa)
        Locale.setDefault(lokal)
        val konfigurasi = Configuration()
        konfigurasi.locale = lokal
        baseContext.resources.updateConfiguration(konfigurasi, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("bahasa", bahassa)
        editor.apply()
        bahasa = bahassa
    }

    private fun loadBahas(){
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val bahaasa = sharedPreferences.getString("bahasa", "")
        if(bahaasa != null){
            setBahassa(bahaasa)
        }
    }
}