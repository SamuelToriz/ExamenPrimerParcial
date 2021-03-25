package com.example.alantorizexamen

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.Tools.PermissionsApplications
import com.example.alantorizexamen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listSurvey = ListSurvey()
    private var id:Int=-2
    private var id2:Int=-4
    private val permission = PermissionsApplications(this@HomeActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra(Constants.ID, -2)

        supportActionBar?.setTitle("Home")
        listSurvey.showListSurveys()

        val adapter = ArrayAdapter<String>(this@HomeActivity, android.R.layout.simple_list_item_1, listSurvey.getStringArray(id))
        binding.ltvSurvey.adapter = adapter

        if(!permission.hasPermissions(Constants.LOCATION))
        {
            permission.acceptPermissionLocation(Constants.LOCATION, 1)
        }



        binding.ltvSurvey.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val survey = listSurvey.getSurvey(position)
            if(!permission.hasPermissions(Constants.MICROPHONE))
            {
                permission.acceptPermissionMicrophone(Constants.MICROPHONE, 1)
            }
            else
            {
                miDialogo(position, survey.nameSurvey).show()
            }

        }
    }

    ///Cuando recarga el activity le pasamos el adapatador donde tenemos la lista y se lo pasamos al LISTVIEW
        override fun onRestart() {
            super.onRestart()
        id2 = intent.getIntExtra(Constants.ID, -2)
            val adapter = ArrayAdapter<String>(this@HomeActivity, android.R.layout.simple_list_item_1, listSurvey.getStringArray(id2))
            binding.ltvSurvey.adapter = adapter
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menuhome, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {

                R.id.itmlistCrearEncuesta -> {
                    val intent = Intent(this@HomeActivity, SurveyActivity::class.java).apply {
                        putExtra(Constants.ID, id)
                    }
                    startActivity(intent)
                }

                R.id.itmVerLista -> {
                    val intent = Intent(this@HomeActivity, MyListActivity::class.java).apply {
                       putExtra(Constants.ID, id)
                    }
                    startActivity(intent)
                }

                R.id.itmlisSalir -> {
                    finish()
                    //Termina el sistema
                    super.onBackPressed()
                }
            }

            return super.onOptionsItemSelected(item)
        }


        private fun miDialogo(position: Int, name: String): AlertDialog {
            val miAlerta = AlertDialog.Builder(this@HomeActivity)
            miAlerta.setTitle("Editar, Ver o Eliminar")
            miAlerta.setMessage("Que desea hacer??")

            miAlerta.setPositiveButton("Eliminar") { _, _ ->
                var answer = listSurvey.delete(name)
                if (answer == true) {
                    Toast.makeText(this@HomeActivity, "Survey eliminada", Toast.LENGTH_SHORT).show()
                    val adapter = ArrayAdapter<String>(this@HomeActivity, android.R.layout.simple_list_item_1, listSurvey.getStringArray(id))
                    binding.ltvSurvey.adapter = adapter
                } else {
                    Toast.makeText(this@HomeActivity, "Survey NO eliminado", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            miAlerta.setNegativeButton("Editar") { _, _ ->

                val intent = Intent(this@HomeActivity, EditActivity::class.java).apply {
                    ///Pasa valores por la posicion
                    putExtra(Constants.ID, position)
                }
                startActivity(intent)
            }
            miAlerta.setNeutralButton("Ver") { _, _ ->
                val intent = Intent(this@HomeActivity, DetailActivity::class.java).apply {

                    putExtra(Constants.ID, position)
                }
                startActivity(intent)
            }

            return miAlerta.create()
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode)
        {
            1-> {
                if(grantResults[0]!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this@HomeActivity, "Es obligatorio ACEPTAR ESTE PERMISO para utilizar la app", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
