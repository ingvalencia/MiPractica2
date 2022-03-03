package com.example.mipractica2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mipractica2.databinding.ActivityDetailsBinding
import com.example.mipractica2.db.DbMangas
import com.example.mipractica2.model.Manga

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dbMangas: DbMangas
    var manga: Manga? = null
    var id:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
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


                tietTitulo.inputType= InputType.TYPE_NULL
                tietGender.inputType= InputType.TYPE_NULL
                tietCreator.inputType= InputType.TYPE_NULL


            }
        }


    }

    fun click(view: View) {

        when(view.id){
            R.id.btnEdit ->{
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }

            R.id.btnDelete ->{
                AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Realmente deseas eliminar el manga ${manga?.title}?")
                    .setPositiveButton("Si",DialogInterface.OnClickListener { dialog, which ->
                        if (dbMangas.deleteManga(id)){
                            Toast.makeText(this, "Registro eliminado exitosamente. ", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this,PrincipalActivity::class.java))
                            finish()
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                    })
                    .show()

            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,PrincipalActivity::class.java))
        finish()
    }



}