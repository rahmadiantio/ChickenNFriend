package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter.UserListAdapter
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var userViewMoldel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        userViewMoldel = ViewModelProvider(this).get(UserViewModel::class.java)
        val tombolLogin = view.findViewById<Button>(R.id.btn_login)
        tombolLogin.setOnClickListener(){
            if(!cekInternetKoneksi()){
                Toast.makeText(requireContext(), "Harap Koneksi Internet Dinyalakan",
                    Toast.LENGTH_LONG).show()
            }
            else {
                cekLogin()
            }
        }

        val tombolRegis = view.findViewById<Button>(R.id.btn_register)
        tombolRegis.setOnClickListener(){
            (context as MainActivity).loadFragment(RegisterFragment.newInstance(), true)
        }

        val tombolGanti = view.findViewById<Button>(R.id.btn_eng)
        tombolGanti.setOnClickListener(){
            if((context as MainActivity).bahasa.equals("in")){
                (context as MainActivity).gantiBahasa("en")
            }
            else{
                (context as MainActivity).gantiBahasa("in")
            }
        }
        return view
    }

    fun cekInternetKoneksi(): Boolean{
        val cm : ConnectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting)
    }

    private fun cekLogin(){
        var userLogin: UserEntity? = null
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val adapter = UserListAdapter()
        userViewMoldel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewMoldel.readAllData.observe(viewLifecycleOwner, Observer { user -> adapter.setData(user)
            userLogin = adapter.cekLogin(username, password)
        })
        (context as MainActivity).login(userLogin)
    }

    companion object{
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}