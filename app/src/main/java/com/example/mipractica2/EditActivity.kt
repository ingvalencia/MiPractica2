package com.example.mipractica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.example.mipractica2.databinding.ActivityDetailsBinding
import com.example.mipractica2.databinding.ActivityEditBinding
import com.example.mipractica2.db.DbMangas
import com.example.mipractica2.model.Manga

class EditActivity : AppCompatActivity() {

    private lateinit var binding:ActivityEditBinding

    private lateinit var dbMangas: DbMangas
    var manga: Manga? = null
    var id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("ID",0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbMangas = DbMangas(this)

        manga = dbMangas.getManga(id)

        if (manga != null){
            with(binding){
                tietTitulo.setText(manga?.title)
                tietGender.setText(manga?.gender)
                tietCreator.setText(manga?.creator)

            }
        }



    }

    fun click(view: View) {

        with(binding){
            if (!tietTitulo.text.toString().isEmpty() && !tietGender.text.toString().isEmpty() && !tietCreator.text.toString().isEmpty()){
                if (dbMangas.updateManga(id,tietTitulo.text.toString(),tietGender.text.toString(),tietCreator.text.toString())){
                    Toast.makeText(this@EditActivity, "Registro Actualizado exitosamente. ", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                    intent.putExtra("ID",id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, "No se puedo realizar la actualización. ", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditActivity, "Ningún campo puede quedar vacio. ", Toast.LENGTH_LONG).show()
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,PrincipalActivity::class.java))
        finish()
    }


}