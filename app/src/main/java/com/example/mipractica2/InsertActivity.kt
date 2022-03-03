package com.example.mipractica2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mipractica2.databinding.ActivityInsertBinding
import com.example.mipractica2.db.DbMangas
import java.util.*

class InsertActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityInsertBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mangas = resources.getStringArray(R.array.manga_generos)
        val adapter = ArrayAdapter(
            this,
            R.layout.list_item2,
            mangas
        )

        with(binding.tietGender){

            setAdapter(adapter)
            onItemClickListener = this@InsertActivity

        }

    }


    //Botón agregar manga
    fun click(view: View) {

        val dbMangas = DbMangas(this)

        with(binding){

            if (!tietTitulo.text.toString().isEmpty() && !tietGender.text.toString().isEmpty() && !tietCreator.text.toString().isEmpty()){

                val id = dbMangas.insertManga(tietTitulo.text.toString(),tietGender.text.toString(),tietCreator.text.toString())

                if (id > 0){
                    Toast.makeText(this@InsertActivity, "Registro guardado exitosamente. ", Toast.LENGTH_LONG).show()
                    tietTitulo.setText("")
                    tietGender.setText("")
                    tietCreator.setText("")
                    tietTitulo.requestFocus()
                }else{
                    Toast.makeText(this@InsertActivity, "Error al guardar el registro. ", Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this@InsertActivity, "Favor de llenar los campos. ", Toast.LENGTH_LONG).show()
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,PrincipalActivity::class.java))
        finish()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

        Toast.makeText(this@InsertActivity,item, Toast.LENGTH_SHORT).show()
    }





}