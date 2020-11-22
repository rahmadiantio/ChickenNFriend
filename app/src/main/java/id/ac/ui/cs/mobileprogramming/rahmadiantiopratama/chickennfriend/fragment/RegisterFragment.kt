package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private lateinit var userViewMoldel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        userViewMoldel = ViewModelProvider(this).get(UserViewModel::class.java)
        val tombolRegis = view.findViewById<Button>(R.id.btn_register_r)
        tombolRegis.setOnClickListener(){
            registerUser()
        }
        return view
    }

    private fun registerUser(){
        var userBaru: UserEntity? = null
        val nama = et_nama.text.toString()
        val username = et_username_r.text.toString()
        val email = et_email.text.toString()
        val password = et_password_r.text.toString()
        var valid = cekInput(nama, username, email, password)
        if(valid){
            userBaru = UserEntity(
                0, nama, email, username, password
            )
            userViewMoldel.tambahUser(userBaru)
        }
        (context as MainActivity).login(userBaru)
    }

    private fun cekInput(nama: String, username: String, email:String, password:String) : Boolean{
        return (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(password))
    }

    companion object{
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}