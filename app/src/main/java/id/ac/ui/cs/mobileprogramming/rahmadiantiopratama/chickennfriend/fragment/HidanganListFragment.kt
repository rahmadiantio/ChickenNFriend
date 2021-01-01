package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.HidanganListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.HidanganViewModel
import kotlinx.android.synthetic.main.fragment_hidangan_list.view.*

class HidanganListFragment : Fragment() {
    private lateinit var hidanganViewModel: HidanganViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hidangan_list, container, false)
        val nama = (context as MainActivity).userLogin!!.nama.toString()
        view.greet.text = sapaan(nama)
        val adapter = HidanganListAdapter()
        val recyclerview = view.list_all_recipe
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        hidanganViewModel = ViewModelProvider(this).get(HidanganViewModel :: class.java)
        hidanganViewModel.readAllData.observe(viewLifecycleOwner, Observer { hidangan ->
            adapter.setData(hidangan)
        })

        val tombolHidangan = view.findViewById<FloatingActionButton>(R.id.add_recipe)
        tombolHidangan.setOnClickListener(){
            (context as MainActivity).loadFragment(TambahHidanganFragment.newInstance(), true)
        }

        val tombolGL = view.findViewById<FloatingActionButton>(R.id.gl_play)
        tombolGL.setOnClickListener(){
            (context as MainActivity).loadFragment(GLFragment.newInstance(), true)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_transaksi, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.komentar){
            (context as MainActivity).loadFragment(KomentarListFragment.newInstance(), true)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        @JvmStatic
        fun newInstance() = HidanganListFragment()
    }

    init {
        System.loadLibrary("cpp_lib")
    }

    external fun sapaan(nama: String) : String
}