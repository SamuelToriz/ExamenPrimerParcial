package com.example.alantorizexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

private lateinit var binding : ActivityLoginBinding
private val listUsers = ListUsers()

override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)
    supportActionBar?.setTitle("Login")

    binding.btnIniciarSesion.setOnClickListener() {
        if(binding.editTextPassword.text.isNotEmpty() && binding.editCorreo.text.isNotEmpty())
        {
            val user = EntityUser()

            user.email = binding.editCorreo.text.toString()
            user.password = binding.editTextPassword.text.toString()

            val index = listUsers.existEmailFilter(user.email)
            val indexx = listUsers.existPasswordFilter(user.password)

            if (index == true && indexx==true)
            {
                Toast.makeText(this@LoginActivity, "Usuario encontrado", Toast.LENGTH_SHORT).show()
                cleanControls()
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)

            } else
            {
                Snackbar.make(it, "Usuario NO encontrado", Snackbar.LENGTH_LONG).show()
            }
        }
        else
        {
            Snackbar.make(it, "Todos los campos son OBLIGATORIOS", Snackbar.LENGTH_LONG).show()
        }
    }
}

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menulogin, menu)
    return super.onCreateOptionsMenu(menu)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when(item.itemId){

        R.id.itmList->{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    return super.onOptionsItemSelected(item)
}

fun cleanControls()
{
    binding.editTextPassword.setText("")
    binding.editCorreo.setText("")
}
}