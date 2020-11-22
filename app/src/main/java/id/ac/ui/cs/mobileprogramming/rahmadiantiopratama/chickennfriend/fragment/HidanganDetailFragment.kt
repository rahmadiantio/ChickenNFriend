package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.app.AlertDialog
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.HidanganListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.UserListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.HidanganViewModel
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_hidangan_detail.*
import kotlinx.android.synthetic.main.fragment_hidangan_detail.view.*
import kotlinx.android.synthetic.main.fragment_hidangan_list.view.*
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.*
import kotlinx.android.synthetic.main.fragment_hidangan_tambah.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class HidanganDetailFragment : Fragment() {
    private lateinit var hidanganViewModel: HidanganViewModel
    private var idHidangan: Int = 0
    private lateinit var namaHidangan: String
    private var foto: Bitmap? = null
    private var fotoPath: String? = null
    private lateinit var sausHidangan: String
    private lateinit var minumanHidangan: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View?= null
        val orientasi = resources.configuration.orientation
        if(orientasi == Configuration.ORIENTATION_LANDSCAPE){
            view = inflater.inflate(R.layout.fragment_hidangan_detail, container, false)
        }
        else{
            view = inflater.inflate(R.layout.fragment_hidangan_detail, container, false)
        }

        idHidangan = this.requireArguments().getInt("id_hidangan")
        namaHidangan = this.requireArguments().getString("nama_hidangan").toString()
        sausHidangan = this.requireArguments().getString("saus_hidangan").toString()
        minumanHidangan = this.requireArguments().getString("minuman").toString()
        fotoPath = this.requireArguments().getString("foto_path")
        foto = (context as MainActivity).fotoDiambil

        view.edt_name.text = namaHidangan
        view.edt_sauce.text = sausHidangan
        view.edt_drink.text = minumanHidangan
        if(foto != null){
            view.recipe_photo.setImageBitmap(foto)
        }
        hidanganViewModel = ViewModelProvider(this).get(HidanganViewModel::class.java)
        val tombolEdit =view.findViewById<Button>(R.id.edit)
        tombolEdit.setOnClickListener(){
            (context as MainActivity).passHidangan(TambahHidanganFragment.newInstance(),
            HidanganEntity(idHidangan, namaHidangan, fotoPath, sausHidangan, minumanHidangan))
        }

        val tombolBuat = view.findViewById<Button>(R.id.create)
        tombolBuat.setOnClickListener(){
//            (context as MainActivity).loadFragment(TimerFragment.newInstance(), false)
        }

        val tombolHapus = view.findViewById<Button>(R.id.delete)
        tombolHapus.setOnClickListener(){
            hapusHidangan()
        }

        val tombolKomentar = view.findViewById<Button>(R.id.komen)
        tombolKomentar.setOnClickListener(){
            (context as MainActivity).loadFragment(TambahKomentarFragment.newInstance(), false)
        }
        return view
    }

    private fun hapusHidangan(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Iya"){_,_->
            hidanganViewModel.hapusHidangan(
                HidanganEntity(idHidangan, namaHidangan, fotoPath, sausHidangan, minumanHidangan))
            (context as MainActivity).loadFragment(HidanganListFragment.newInstance(), false)
        }
        builder.setNegativeButton("Tidak"){_,_->}
        builder.setTitle("Ingin menghapus hidangan?")
        builder.create().show()
    }

    companion object{
        @JvmStatic
        fun newInstance() = HidanganDetailFragment()
    }
}