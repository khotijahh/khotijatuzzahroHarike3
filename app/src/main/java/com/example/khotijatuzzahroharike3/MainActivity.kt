package com.example.khotijatuzzahroharike3

import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var inputId : EditText
    private lateinit var inputNama : EditText
    private lateinit var jBkKomik : RadioButton
    private lateinit var jBkNovel : RadioButton
    private lateinit var tambahData : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputId = findViewById(R.id.txtInputId)
        inputNama = findViewById(R.id.txtInputBuku)
        jBkKomik = findViewById(R.id.rbKomik)
        jBkNovel = findViewById(R.id.rbNovel)
        tambahData = findViewById(R.id.btnTambah)
        recyclerView = findViewById(R.id.ListData)
        // membuat variabel kosong tipe array MutableList untuk simpan data array
        // data array di simpan di data class perpusData

        val data = mutableListOf<PerpusData>()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = perpusAdapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager
        //setOnclikListener untuk tombol tambah data

        tambahData.setOnClickListener {
            //1. membuat variabel penyimpanan data
            val id = inputId.text.toString() // editText.text => String
            val nama = inputNama.text.toString()
            var jBk = ""
            if (jBkKomik.isChecked){
                jBk = "KOMIK"
            } else{
                jBk = "NOVEL"
            }
            //simpan data ke array mutableList
            val dataPerpus = PerpusData(id,nama,jBk)
            data.add(dataPerpus)
            recyclerAdapter.notifyDataSetChanged()
        }
    }
}