package com.example.alantorizexamen

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.Tools.PermissionsApplications
import com.example.alantorizexamen.databinding.ActivitySurveyBinding
import com.google.android.material.snackbar.Snackbar


class SurveyActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySurveyBinding
    private val listSurvey = ListSurvey()
    private val listUser = ListUsers();
    private val permission = PermissionsApplications(this@SurveyActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Survey")

        binding.btnCrear.setOnClickListener() {
            if(!permission.hasPermissions(Constants.STORAGE))
            {
                permission.acceptPermissionStorage(Constants.STORAGE, 1)
            }
            else
            {
                validateEmptyControls()
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            1-> {
                if(grantResults[0]!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this@SurveyActivity, "Es obligatorio ACEPTAR ESTE PERMISO para utilizar la app", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    validateEmptyControls()
                }
            }
        }
    }

    fun validateEmptyControls()
    {
        if(binding.editTextName.text.isNotEmpty() && binding.spnGender.selectedItemPosition!=0 && binding.rgbFood.checkedRadioButtonId!=-1 )
        {
            val survey = EntitySurvey()
            val user = EntityUser()

            survey.id = user.id
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
    }
}