package com.example.alantorizexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private val listSurvey = ListSurvey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Detail")

        val id:Int = intent.getIntExtra(Constants.ID, -1)

        if(id!=-1)
        {
            val survey = listSurvey.getSurvey(id)

            binding.txtvName.text = "${survey.nameSurvey}"
            binding.txtGender.text = "${if(survey.gender==1) "Masculino" else if(survey.gender==2) "Femenino" else "Genero no seleccionado"}"
            binding.txtvRelative.text = "${if(survey.recomend) "Nos recomendaria" else "No nos recomendaria"}"
            binding.txtvFood.text = "Comida Favorita: ${survey.food}"
            binding.txtvDrink.text = "Bebida Favorita: ${if(survey.cerveza) "Cerveza" else ""} ${if(survey.malteada) "Malteada" else ""}" +
                    " ${if(survey.refresco) "Refresco" else "Sin Preferencia"}"

        }
        else
        {
            Toast.makeText(this@DetailActivity, "Se genero un problema al cargar esta actividad", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}