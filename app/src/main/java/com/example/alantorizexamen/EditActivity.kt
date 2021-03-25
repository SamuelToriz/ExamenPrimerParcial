package com.example.alantorizexamen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.util.*

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

            binding.ckbFacebook.isChecked = survey.facebook
            binding.ckbInstagram.isChecked = survey.Instagram
            binding.spnLocation.setSelection(survey.Zone)
            binding.spnTipoPedido.setSelection(survey.typeOrder)
            binding.swtApp.isChecked = survey.appMovil
            binding.edtAge.setText(survey.age)

            binding.editDate.setText(survey.date)
            binding.editTime.setText(survey.time)

            when (survey.food)
            {
                "Wings" ->
                {
                    binding.rgbFood.check(binding.rdbWings.id)
                }
                "Burgers" -> {
                    binding.rgbFood.check(binding.rdbBurgers.id)
                }
                "Hotdogs" -> {
                    binding.rgbFood.check(binding.rdbHotdogs.id)
                }
            }
            binding.btnEditar.setOnClickListener() {
                if(binding.editTextName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0 && binding.rgbFood.checkedRadioButtonId!=-1
                        && binding.spnLocation.selectedItemPosition!=0 && binding.spnTipoPedido.selectedItemPosition!=0 && binding.editDate.text.isNotEmpty()
                        && binding.editTime.text.isNotEmpty())
                {
                    val survey = EntitySurvey()

                    survey.nameSurvey = binding.editTextName.text.toString()
                    survey.nameSurvey = binding.editTextName.text.toString()
                    survey.age = binding.edtAge.text.toString()
                    survey.Zone = binding.spnLocation.selectedItemPosition
                    survey.typeOrder = binding.spnTipoPedido.selectedItemPosition
                    survey.gender = binding.spnGender.selectedItemPosition
                    survey.date = binding.editDate.text.toString()
                    survey.time = binding.editTime.text.toString()

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

                    survey.facebook = binding.ckbFacebook.isChecked
                    survey.Instagram = binding.ckbInstagram.isChecked

                    survey.appMovil = binding.swtApp.isChecked
                    survey.recomend = binding.stwFamiliar.isChecked

                    val index = listSurvey.edit(id,survey)
                    miDialogo(survey.nameSurvey).show()

                }
                else
                {
                    Snackbar.make(it, "Todos los campos son OBLIGATORIOS", Snackbar.LENGTH_LONG).show()
                }
            }

            binding.editDate.setOnClickListener {
                val myCalendar = Calendar.getInstance()

                var year = myCalendar.get(Calendar.YEAR)
                var month = myCalendar.get(Calendar.MONTH)
                var day = myCalendar.get(Calendar.DAY_OF_MONTH)

                /// y,m,d son las que se detonan en el evento
                val dpd = DatePickerDialog(this@EditActivity, DatePickerDialog.OnDateSetListener { view, y, m, d ->



                    binding.editDate.setText("${twoDigits(d)}/${twoDigits(m+1)}/$y")

                },year, month, day)

                dpd.show()
            }

            binding.editTime.setOnClickListener {
                val myCalendar = Calendar.getInstance()
                val hourOfDay = myCalendar.get(Calendar.HOUR_OF_DAY)
                val minute = myCalendar.get(Calendar.MINUTE)

                val tpd = TimePickerDialog(this@EditActivity, TimePickerDialog.OnTimeSetListener { view, h, m ->

                    binding.editTime.setText("${twoDigits(h)}:${twoDigits(m)}")

                },hourOfDay, minute,false)

                tpd.show()
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
        binding.ckbCerveza .isChecked = false
        binding.ckbMalteada.isChecked = false
        binding.ckbRefresco.isChecked = false
        binding.stwFamiliar.isChecked = false
        binding.ckbFacebook.isChecked = false
        binding.ckbInstagram.isChecked = false
        binding.swtApp.isChecked = false
        binding.edtAge.setText("")
        binding.spnLocation.setSelection(0)
        binding.spnTipoPedido.setSelection(0)
        binding.editTime.setText("")
        binding.editDate.setText("")
    }

    fun twoDigits(number:Int):String
    {
        return if(number<=9) "0$number" else number.toString()
    }
}