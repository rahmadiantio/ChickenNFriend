package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.UserListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.HidanganViewModel
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.KomentarViewModel
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.*
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.view.*
import kotlinx.android.synthetic.main.fragment_komentar_tambah.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class TambahKomentarFragment : Fragment() {
    private lateinit var komentarViewModel: KomentarViewModel
    private var idKomentar: Int = 0
    private lateinit var komentar: String
    private var status: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_komentar_tambah, container, false)
        if(this.arguments != null){
            idKomentar = this.requireArguments().getInt("id_komentar")
            komentar = this.requireArguments().getString("komentar").toString()
        }

        komentarViewModel = ViewModelProvider(this).get(KomentarViewModel::class.java)

        val tombolSubmit = view.findViewById<Button>(R.id.submit_komen)
        tombolSubmit.setOnClickListener(){
            registerKomentar()
        }

        return view
    }

    private fun registerKomentar(){
        val kom = edt_komentar.text.toString()
        var valid = cekInput(kom)
        if(valid){
            val komen = KomentarEntity(idKomentar, kom, status)
            komentarViewModel.tambahKomentar(komen)
        }
        (context as MainActivity).loadFragment(HidanganListFragment.newInstance(), false)
    }

    private fun cekInput(kom: String) : Boolean{
        return (!TextUtils.isEmpty(kom))
    }

    companion object{
        @JvmStatic
        fun newInstance() = TambahKomentarFragment()
    }
}