package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.KomentarListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.KomentarViewModel
import kotlinx.android.synthetic.main.fragment_komentar_list.view.*

class KomentarListFragment : Fragment() {
    private lateinit var komentarViewModel: KomentarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_komentar_list, container, false)
        val adapter = KomentarListAdapter()
        val recyclerView = view.list_all_komentar
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        komentarViewModel = ViewModelProvider(this).get(KomentarViewModel::class.java)
        komentarViewModel.readAllData.observe(viewLifecycleOwner, Observer { komentar ->
            adapter.setData(komentar)
        })
        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() = KomentarListFragment()
    }
}