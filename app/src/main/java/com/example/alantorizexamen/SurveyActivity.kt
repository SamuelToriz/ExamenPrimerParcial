package com.example.alantorizexamen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.Data.SurveyDb
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.Tools.PermissionsApplications
import com.example.alantorizexamen.databinding.ActivitySurveyBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


class SurveyActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySurveyBinding
    private val listSurvey = ListSurvey()
    private val surveylist = SurveyDb(this@SurveyActivity)
    private var id:Int =-3
    private val permission = PermissionsApplications(this@SurveyActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Survey")
        id = intent.getIntExtra(Constants.ID, -3)

        binding.btnCrear.setOnClickListener() {
            if(!permission.hasPermissions(Constants.STORAGE))
            {
                permission.acceptPermission(Constants.STORAGE, 1)
            }
            else
            {
                validateEmptyControls()
            }


        }

        binding.editDate.setOnClickListener {
            val myCalendar = Calendar.getInstance()

            var year = myCalendar.get(Calendar.YEAR)
            var month = myCalendar.get(Calendar.MONTH)
            var day = myCalendar.get(Calendar.DAY_OF_MONTH)

            /// y,m,d son las que se detonan en el evento
            val dpd = DatePickerDialog(this@SurveyActivity, DatePickerDialog.OnDateSetListener { view, y, m, d ->



                binding.editDate.setText("${twoDigits(d)}/${twoDigits(m+1)}/$y")

            },year, month, day)

            dpd.show()
        }

        binding.editTime.setOnClickListener {
            val myCalendar = Calendar.getInstance()
            val hourOfDay = myCalendar.get(Calendar.HOUR_OF_DAY)
            val minute = myCalendar.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this@SurveyActivity, TimePickerDialog.OnTimeSetListener { view, h, m ->

                binding.editTime.setText("${twoDigits(h)}:${twoDigits(m)}")

            },hourOfDay, minute,false)

            tpd.show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            1-> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    validateEmptyControls()
                }
                else
                {
                    Toast.makeText(this@SurveyActivity, "Es obligatorio ACEPTAR ESTE PERMISO para utilizar la app", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    fun validateEmptyControls()
    {
        if(binding.editTextName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0 && binding.rgbFood.checkedRadioButtonId!=-1 &&
                binding.spnLocation.selectedItemPosition!=0 && binding.spnTipoPedido.selectedItemPosition!=0 && binding.editDate.text.isNotEmpty()
                && binding.editTime.text.isNotEmpty())
        {
            val survey = EntitySurvey()

            survey.id = id
            Log.d(Constants.LOG_TAG,"PRUEBA" + id.toString())
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


            val index = listSurvey.add(survey)

            val x :Long = surveylist.add(survey)
            Log.d(Constants.LOG_TAG, "hola" + x.toString())

            if (index >= 0)
            {
                Toast.makeText(this@SurveyActivity, "Survey guardado", Toast.LENGTH_SHORT).show()
                cleanControls()
                val intent = Intent(this@SurveyActivity, HomeActivity::class.java).apply {
                    putExtra(Constants.ID, id)
                }
                startActivity(intent)
                finish()


            } else {
                Snackbar.make(findViewById(android.R.id.content), "Survey NO guardado ", Snackbar.LENGTH_LONG).show()
            }
        }
        else
        {
            Snackbar.make(findViewById(android.R.id.content), "Todos los campos son OBLIGATORIOS", Snackbar.LENGTH_LONG).show()
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