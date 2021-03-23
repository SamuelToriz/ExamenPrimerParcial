package com.example.alantorizexamen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alantorizexamen.Adapters.SurveyAdapter
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.databinding.ActivityMylistactivityBinding

class MyListActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMylistactivityBinding
    private val listSurvey = ListSurvey()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMylistactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("My List Activity")

        buildRecyclerView()


    }

    override fun onRestart() {
        super.onRestart()
        buildRecyclerView()
    }

    fun buildRecyclerView()
    {
        val list = listSurvey.getListSurveyArray()
        val adapter = SurveyAdapter(list, this@MyListActivity)
        val linearLayout = LinearLayoutManager(this@MyListActivity, LinearLayoutManager.VERTICAL, false)

        binding.rvwUser.layoutManager = linearLayout
        binding.rvwUser.setHasFixedSize(true)
        binding.rvwUser.adapter = adapter
    }

}