package com.example.khotijatuzzahroharike3

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class perpusAdapter(private val dataSet:MutableList<PerpusData>):
        RecyclerView.Adapter<perpusAdapter.PerpusHolder>(){
    class PerpusHolder(view: View):RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.txtDataId)
        val namaBk = view.findViewById<TextView>(R.id.txtDataNamaBk)
        val jenisBK = view.findViewById<TextView>(R.id.txtJenisBk)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        val btnHapus = view.findViewById<Button>(R.id.btnHapus)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerpusHolder {
       val view = LayoutInflater.from(parent.context).inflate(
           R.layout.activity_perpus_adapter,parent,false
       )
        return PerpusHolder(view)
    }

    override fun onBindViewHolder(holder: PerpusHolder, position: Int) {
        holder.id.text = dataSet[position].id
        holder.namaBk.text = dataSet[position].namaBuku
        holder.jenisBK.text = dataSet[position].jenisBk
        holder.btnHapus.setOnClickListener {
            dataSet.removeAt(position)
            notifyItemRangeRemoved(position, dataSet.size)
            notifyDataSetChanged()
        }

        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.activity_edit_perpus,null)
            val eId = view.findViewById<TextView>(R.id.editId_Update)
            val eNamaBk = view.findViewById<TextView>(R.id.editNama_Update)
            val jBk = view.findViewById<TextView>(R.id.tVjenisBK)
            val ejBkKomik = view.findViewById<RadioButton>(R.id.rbKomik)
            val ejBKNovel = view.findViewById<RadioButton>(R.id.rbNovel)
            //
            eId.text = dataSet[position].id
            eNamaBk.text = dataSet[position].namaBuku
            jBk.text = dataSet[position].jenisBk

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("edit data")
                .setView(view)
                .setPositiveButton("Update",DialogInterface.OnClickListener{dialogInterface, i ->
                    dataSet[position].id = eId.text.toString()
                    dataSet[position].namaBuku = eNamaBk.text.toString()
                    if (ejBkKomik.isChecked){
                        dataSet[position].jenisBk = "KOMIK"
                    } else {
                        dataSet[position].jenisBk = "NOVEL"
                    }
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener{dialogInterface, i ->  })
            alertDialog.create().show()
        }
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }

}