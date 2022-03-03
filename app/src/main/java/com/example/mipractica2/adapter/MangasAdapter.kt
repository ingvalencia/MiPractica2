package com.example.mipractica2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.mipractica2.databinding.ListItemBinding
import com.example.mipractica2.model.Manga

class MangasAdapter(contexto: Context,listManga: ArrayList<Manga>): BaseAdapter() {


    private val listManga = listManga
    private val layoutInflater = LayoutInflater.from(contexto)


    override fun getCount(): Int {
        return listManga.size
    }

    override fun getItem(position: Int): Any {
        return listManga[position]
    }

    override fun getItemId(position: Int): Long {
        return listManga[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ListItemBinding.inflate(layoutInflater)

        with(binding){
            tvTitle.text = listManga[position].title
            tvGenre.text = listManga[position].gender
            tvDeveloper.text = listManga[position].creator
        }
        return binding.root
    }
}