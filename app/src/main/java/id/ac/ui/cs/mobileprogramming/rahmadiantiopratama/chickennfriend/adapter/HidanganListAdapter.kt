package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.HidanganEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment.HidanganDetailFragment
import kotlinx.android.synthetic.main.hidangan_row_layout.view.*

class HidanganListAdapter: RecyclerView.Adapter<HidanganListAdapter.MyViewHolder>(){
    private var listHidangan = emptyList<HidanganEntity>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): HidanganListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hidangan_row_layout,
        parent, false))
    }

    override fun getItemCount(): Int {
        return listHidangan.size
    }

    override fun onBindViewHolder(holder: HidanganListAdapter.MyViewHolder, position: Int) {
        val itemSekarang = listHidangan[position]
        holder.itemView.txt_row_nama.text = itemSekarang.nama
        holder.itemView.setOnClickListener(){
            (holder.itemView.context as MainActivity).passHidangan(
                HidanganDetailFragment.newInstance(), itemSekarang
            )
        }
    }

    fun setData(hidanganEntity: List<HidanganEntity>){
        this.listHidangan = hidanganEntity
        notifyDataSetChanged()
    }

    fun getHidangan(id: Int): HidanganEntity?{
        for(hidangan in this.listHidangan){
            if(hidangan.id == id){
                return hidangan
            }
        }
        return null
    }
}