package com.erikriosetiawan.mypreloaddata.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erikriosetiawan.mypreloaddata.R
import com.erikriosetiawan.mypreloaddata.databinding.ItemMahasiswaRowBinding
import com.erikriosetiawan.mypreloaddata.model.MahasiswaModel
import kotlin.random.Random

class MahasiswaAdapter : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaHolder>() {

    private val listMahasiswa = ArrayList<MahasiswaModel>()

    fun setData(listMahasiswa: ArrayList<MahasiswaModel>) {
        if (listMahasiswa.size > 0) {
            this.listMahasiswa.clear()
            this.listMahasiswa.addAll(listMahasiswa)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa_row, parent, false)
        return MahasiswaHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaHolder, position: Int) {
        holder.bind(listMahasiswa[position])
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = listMahasiswa.size

    inner class MahasiswaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMahasiswaRowBinding.bind(itemView)
        fun bind(mahasiswa: MahasiswaModel) {
            val random = Random.Default
            val color =
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            binding.apply {
                tvNim.text = mahasiswa.nim
                tvName.text = mahasiswa.name
                imageView.setColorFilter(color)
            }
        }
    }
}