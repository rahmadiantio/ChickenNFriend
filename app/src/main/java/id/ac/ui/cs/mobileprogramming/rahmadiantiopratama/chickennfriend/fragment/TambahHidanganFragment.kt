package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.HidanganViewModel
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.*
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.view.*

class TambahHidanganFragment : Fragment() {
    private lateinit var hidanganViewModel: HidanganViewModel
    private var idHidangan: Int = 0
    private lateinit var namaHidangan: String
    private var foto: Bitmap? = null
    private var fotoPath: String? = null
    private lateinit var sausHidangan: String
    private lateinit var minumanHidangan: String
    private var CAMERA = Manifest.permission.CAMERA
    private var flagRequestPermissionCalled = false
    private var permissionRequestCounter = 0
    private var CAMERA_REQUEST_CODE = 101

    private fun requestCameraPermission(context: Context, activity: Activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
        ) {
            AlertDialog.Builder(context)
                .setTitle("Membutuhkan izin")
                .setMessage("Meminta Izin Kamera Agar Dapat Digunakan Untuk Mengambil Gambar")
                .setPositiveButton(
                    "Izinkan"
                ) { _, _ ->
                    requestPermissions(
                        arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE
                    )
                }
                .setNegativeButton(
                    "Tidak"
                ) { dialog, _ -> dialog.dismiss() }
                .create().show()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Diizinkan", Toast.LENGTH_SHORT).show();
            } else {
                ++permissionRequestCounter
                if(permissionRequestCounter == 2) {
                    flagRequestPermissionCalled = true
                }
                Toast.makeText(requireContext(), "Ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun checkPermission(permission: String, context: Context): Boolean {
        var check: Int = ContextCompat.checkSelfPermission(context, permission)
        return (check == PackageManager.PERMISSION_GRANTED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hidangan_tambah, container, false)
        if(this.arguments != null){
            idHidangan = this.requireArguments().getInt("id_hidangan")
            namaHidangan = this.requireArguments().getString("nama_hidangan").toString()
            sausHidangan = this.requireArguments().getString("saus_hidangan").toString()
            minumanHidangan = this.requireArguments().getString("minuman").toString()

            view.edt_nama.setText(namaHidangan)
            view.edt_saus.setText(sausHidangan)
            view.edt_minuman.setText(minumanHidangan)

            foto =(context as MainActivity).fotoDiambil
            if(foto != null){
                view.hidangan_foto.setImageBitmap(foto)
            }
        }

        hidanganViewModel = ViewModelProvider(this).get(HidanganViewModel::class.java)
        val tombolFoto = view.findViewById<Button>(R.id.ambil_foto)
        tombolFoto.setOnClickListener {
            if (!checkPermission(CAMERA, requireContext())) {
                if (!flagRequestPermissionCalled) {
                    requestCameraPermission(requireContext(), requireActivity())
                } else {
                    AlertDialog.Builder(context)
                        .setTitle("Izin Ditolak")
                        .setMessage("Izin Ditolak Untuk Aplikasi Ini, Anda Harus Mengaktifkan Secara Manual")
                        .setPositiveButton(
                            "Tutup"
                        ) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create().show()
                }
            } else {
                (context as MainActivity).ambilFoto()
            }
        }

        val tombolLoadFoto = view.findViewById<Button>(R.id.load_foto)
        tombolLoadFoto.setOnClickListener(){
            foto =(context as MainActivity).fotoDiambil
            fotoPath = (context as MainActivity).getFileFoto().absolutePath
            if(foto != null){
                view.hidangan_foto.setImageBitmap(foto)
            }
            Log.i("path", foto.toString())
        }

        val tombolSubmit = view.findViewById<Button>(R.id.submit)
        tombolSubmit.setOnClickListener(){
            registerHidangan()
        }

        return view
    }

    private fun registerHidangan(){
        val nama = edt_nama.text.toString()
        val saus = edt_saus.text.toString()
        val minuman = edt_minuman.text.toString()
        var valid = cekInput(nama, saus, minuman)
        if(valid){
            val hidangan = HidanganEntity(idHidangan, nama, fotoPath, saus, minuman)
            if(hidangan.id == 0){
                hidanganViewModel.tambahHidangan(hidangan)
            }
            else{
                hidanganViewModel.updateHidangan(hidangan)
            }
        }
        (context as MainActivity).loadFragment(HidanganListFragment.newInstance(), false)
    }

    private fun cekInput(nama: String, saus: String, minuman:String) : Boolean{
        return (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(saus) && !TextUtils.isEmpty(minuman))
    }

    companion object{
        @JvmStatic
        fun newInstance() = TambahHidanganFragment()
    }
}