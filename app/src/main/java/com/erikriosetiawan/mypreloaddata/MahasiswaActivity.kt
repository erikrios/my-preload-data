package com.erikriosetiawan.mypreloaddata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.mypreloaddata.adapter.MahasiswaAdapter
import com.erikriosetiawan.mypreloaddata.database.MahasiswaHelper
import com.erikriosetiawan.mypreloaddata.databinding.ActivityMahasiswaBinding

class MahasiswaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMahasiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()
        binding.recyclerView.adapter = mahasiswaAdapter

        val mahasiswaHelper = MahasiswaHelper(this)
        mahasiswaHelper.open()

        val mahasiswaModels = mahasiswaHelper.getAllData()
        mahasiswaHelper.close()

        mahasiswaAdapter.setData(mahasiswaModels)
    }
}