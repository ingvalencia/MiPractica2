package com.example.mipractica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mipractica2.adapter.MangasAdapter
import com.example.mipractica2.databinding.ActivityPrincipalBinding
import com.example.mipractica2.db.DBHelper
import com.example.mipractica2.db.DbMangas
import com.example.mipractica2.model.Manga

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var listMangas: ArrayList<Manga>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Probando la generación de DB
        /*
        val dbHelper = DBHelper(this)
        val db = dbHelper.writableDatabase

        if(db != null){
            Toast.makeText(this,"La bd fue creada exitosamente.", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Error al crear la bd.", Toast.LENGTH_LONG).show()
        }
        */

        val dbMangas = DbMangas(this)

        listMangas = dbMangas.getMangas()

        val mangasAdapter = MangasAdapter(this, listMangas)

        binding.lvmangas.adapter = mangasAdapter

        binding.lvmangas.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ID",id.toInt())
            startActivity(intent)
            finish()


        }





    }

    fun clic(view: View) {

        //Evento del botón flotante
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }


}