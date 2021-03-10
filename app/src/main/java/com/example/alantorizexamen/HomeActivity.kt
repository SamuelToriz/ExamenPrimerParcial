package com.example.alantorizexamen

import android.content.Intent
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
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listSurvey = ListSurvey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Home")
        listSurvey.showListSurveys()

        val adapter = ArrayAdapter<String>(this@HomeActivity, android.R.layout.simple_list_item_1, listSurvey.getStringArray())
        binding.ltvSurvey.adapter = adapter


        binding.ltvSurvey.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->

            val survey = listSurvey.getSurvey(position)
            miDialogo(position, survey.nameSurvey).show()
        }
    }

        override fun onRestart() {
            super.onRestart()
            val adapter = ArrayAdapter<String>(
                this@HomeActivity,
                android.R.layout.simple_list_item_1,
                listSurvey.getStringArray()
            )
            binding.ltvSurvey.adapter = adapter
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menuhome, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.itemId) {

                R.id.itmlistCrearEncuesta -> {
                    val intent = Intent(this@HomeActivity, SurveyActivity::class.java)
                    startActivity(intent)
                }

                R.id.itmlisSalir -> {
                    finish()
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
                    val adapter = ArrayAdapter<String>(
                        this@HomeActivity,
                        android.R.layout.simple_list_item_1,
                        listSurvey.getStringArray()
                    )
                    binding.ltvSurvey.adapter = adapter
                } else {
                    Toast.makeText(this@HomeActivity, "Survey NO eliminado", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            miAlerta.setNegativeButton("Editar") { _, _ ->

                val intent = Intent(this@HomeActivity, EditActivity::class.java).apply {

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
}
