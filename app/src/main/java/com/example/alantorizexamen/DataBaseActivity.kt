package com.example.alantorizexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alantorizexamen.Adapters.SurveyAdapter
import com.example.alantorizexamen.Adapters.UserAdapter
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.SurveyDb
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.databinding.ActivityDataBaseBinding
import com.example.alantorizexamen.databinding.ActivityMylistactivityBinding

class DataBaseActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityDataBaseBinding
    private val listSurvey = ListSurvey()
    private var id:Int=-2

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Data Base Adapter")
        id = intent.getIntExtra(Constants.ID, -2)
        buildRecyclerView()
    }

    override fun onRestart() {
        super.onRestart()
        buildRecyclerView()
    }

    fun buildRecyclerView()
    {
        val list = SurveyDb(this@DataBaseActivity)
        list.getAll()
        val adapter = UserAdapter(list.getOne(id), this@DataBaseActivity)
        val linearLayout = LinearLayoutManager(this@DataBaseActivity, LinearLayoutManager.VERTICAL, false)

        binding.rvwSurvey.layoutManager = linearLayout
        binding.rvwSurvey.setHasFixedSize(true)
        binding.rvwSurvey.adapter = adapter
    }
}