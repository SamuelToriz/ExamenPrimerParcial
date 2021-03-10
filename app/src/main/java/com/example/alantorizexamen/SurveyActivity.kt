package com.example.alantorizexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.databinding.ActivitySurveyBinding
import com.google.android.material.snackbar.Snackbar

class SurveyActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySurveyBinding
    private val listSurvey = ListSurvey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Survey")

        binding.btnCrear.setOnClickListener() {
            if(binding.editTextName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0 && binding.rgbFood.checkedRadioButtonId!=-1 )
            {
                val survey = EntitySurvey()

                survey.nameSurvey = binding.editTextName.text.toString()
                survey.gender = binding.spnGender.selectedItemPosition

                when (binding.rgbFood.checkedRadioButtonId) {
                    binding.rdbWings.id -> {
                       survey.food = "Refresco"
                    }
                    binding.rdbHotdogs.id -> {
                        survey.food = "HotDos"
                    }
                    binding.rdbWings.id -> {
                        survey.food = "Wings"
                    }
                }

                survey.malteada = binding.ckbMalteada.isChecked
                survey.refresco = binding.ckbRefresco.isChecked
                survey.cerveza = binding.ckbCerveza.isChecked

                val index = listSurvey.add(survey)

                if (index >= 0)
                {
                    Toast.makeText(this@SurveyActivity, "Survey guardado", Toast.LENGTH_SHORT).show()
                    cleanControls()
                    val intent = Intent(this@SurveyActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()


                } else {
                    Snackbar.make(it, "Survey NO guardado ", Snackbar.LENGTH_LONG).show()
                }
            }
            else
            {
                Snackbar.make(it, "Todos los campos son OBLIGATORIOS", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun cleanControls()
    {
        binding.editTextName.setText("")
        binding.spnGender.setSelection(0)
        binding.rgbFood.clearCheck()
        binding.ckbCerveza .isChecked = false
        binding.ckbMalteada.isChecked = false
        binding.ckbRefresco.isChecked = false
        binding.stwFamiliar.isChecked = false
    }
}