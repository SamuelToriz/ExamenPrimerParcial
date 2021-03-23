package com.example.alantorizexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.databinding.ActivityEditBinding
import com.google.android.material.snackbar.Snackbar

class EditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEditBinding
    private val listSurvey = ListSurvey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Edit")

        var id:Int = intent.getIntExtra(Constants.ID, -1)
        if(id>-1)
        {
            var survey = listSurvey.getSurvey(id)
            binding.editTextName.setText(survey.nameSurvey)
            binding.spnGender.setSelection(survey.gender)
            binding.stwFamiliar.isChecked = survey.recomend
            binding.ckbCerveza.isChecked = survey.cerveza
            binding.ckbRefresco.isChecked = survey.refresco
            binding.ckbMalteada.isChecked = survey.malteada

            when (survey.food)
            {
                "Wings" ->
                {
                    binding.rgbFood.check(binding.rdbWings.id)
                }
                "Hamburgesas" -> {
                    binding.rgbFood.check(binding.rdbBurgers.id)
                }
                "Hotdogs" -> {
                    binding.rgbFood.check(binding.rdbHotdogs.id)
                }
            }
            binding.btnEditar.setOnClickListener() {
                if(binding.editTextName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0 && binding.rgbFood.checkedRadioButtonId!=-1)
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

                    val index = listSurvey.edit(id,survey)
                    miDialogo(survey.nameSurvey).show()

                }
                else
                {
                    Snackbar.make(it, "Todos los campos son OBLIGATORIOS", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun miDialogo(name : String): AlertDialog
    {
        var id:Int = intent.getIntExtra(Constants.ID, -1)
        var survey = listSurvey.getSurvey(id)
        val miAlerta = AlertDialog.Builder(this@EditActivity)
        miAlerta.setTitle("Confirmar")
        miAlerta.setMessage("Esta seguro??")

        // Toast.makeText(this@Edit_DeleteActivity, "$id", Toast.LENGTH_SHORT).show()
        miAlerta.setPositiveButton("Si"){_,_ ->
            var answer = listSurvey.edit(id, survey)

            if(answer==true)
            {
                Toast.makeText(this@EditActivity, "Survey editado", Toast.LENGTH_SHORT).show()
                cleanControls()
                val intent = Intent(this@EditActivity, MyListActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                Toast.makeText(this@EditActivity, "Survey NO editado", Toast.LENGTH_SHORT).show()
            }

        }
        miAlerta.setNegativeButton("No") {_,_ ->
            Toast.makeText(this@EditActivity, "Edicion Cancelada", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@EditActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        return miAlerta.create()
    }

    fun cleanControls()
    {
        binding.editTextName.setText("")
        binding.spnGender.setSelection(0)
        binding.rgbFood.clearCheck()
        binding.rdbWings.isChecked = false
        binding.rdbBurgers.isChecked = false
        binding.rdbHotdogs.isChecked = false
        binding.stwFamiliar.isChecked = false
    }
}