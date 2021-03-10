package com.example.alantorizexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val listUser = ListUsers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Register")

        binding.btnRegistrar.setOnClickListener() {
            if(binding.editTextName.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty() && binding.editTextPhone.text.isNotEmpty()
                && binding.spnGender.selectedItemPosition!=0)
            {
                val user = EntityUser()

                user.name = binding.editTextName.text.toString()
                user.email = binding.editTextEmail.text.toString()
                user.password = binding.editTextPassword.text.toString()
                user.phone = binding.editTextPhone.text.toString()
                user.gender = binding.spnGender.selectedItemPosition

                val index = listUser.add(user)

                if (index >= 0)
                {
                    Toast.makeText(this@RegisterActivity, "Usuario guardado", Toast.LENGTH_SHORT).show()
                    cleanControls()
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    Snackbar.make(it, "Usuario NO guardado ", Snackbar.LENGTH_LONG).show()
                }
            }
            else
            {
                Snackbar.make(it, "Todos los campos son OBLIGATORIOS excepto Beca y Hobbies ", Snackbar.LENGTH_LONG).show()
            }

        }
    }

    fun cleanControls()
    {
        binding.editTextName.setText("")
        binding.editTextPassword.setText("")
        binding.editTextPhone.setText("")
        binding.spnGender.setSelection(0)
    }
}