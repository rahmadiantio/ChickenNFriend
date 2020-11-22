package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.UserEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import kotlinx.android.synthetic.main.hidangan_row_layout.view.*

class UserListAdapter:RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    private var listUser = emptyList<UserEntity>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.hidangan_row_layout,
        parent, false))
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemSekarang = listUser[position]
        holder.itemView.txt_row_nama.text = itemSekarang.nama
    }

    fun setData(user: List<UserEntity>){
        this.listUser = user
        notifyDataSetChanged()
    }

    fun cekLogin(username: String, password: String): UserEntity?{
        for(user in this.listUser){
            if(user.username.equals(username) && user.password.equals(password)){
                return user
            }
        }
        return null
    }
}