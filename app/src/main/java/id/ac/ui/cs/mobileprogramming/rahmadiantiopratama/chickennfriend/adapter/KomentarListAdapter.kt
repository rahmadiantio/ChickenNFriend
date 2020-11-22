package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.entity.KomentarEntity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import kotlinx.android.synthetic.main.komentar_row_layout.view.*

class KomentarListAdapter: RecyclerView.Adapter<KomentarListAdapter.MyViewHolder>(){
    private var listKomentar = emptyList<KomentarEntity>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.komentar_row_layout,
            parent, false))
    }

    override fun getItemCount(): Int {
        return listKomentar.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemSekarang= listKomentar[position]
        holder.itemView.txt_row_komentar.text = itemSekarang.komentar

        if(itemSekarang.status) {
            holder.itemView.txt_row_status.text = "MANTAP DJIWA"
        }
        else {
            holder.itemView.txt_row_status.text = "MEEEEH"
        }
    }

    fun setData(komentarEntity: List<KomentarEntity>){
        this.listKomentar = komentarEntity
        notifyDataSetChanged()
    }
}