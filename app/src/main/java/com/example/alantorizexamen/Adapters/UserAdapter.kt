package com.example.alantorizexamen.Adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alantorizexamen.Data.ListSurvey
import com.example.alantorizexamen.Data.ListUsers
import com.example.alantorizexamen.DetailActivity
import com.example.alantorizexamen.EditActivity
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.R
import com.example.alantorizexamen.Tools.Constants
import com.example.alantorizexamen.databinding.ItemUserBinding
import com.google.android.material.snackbar.Snackbar

class SurveyAdapter(val surveyList: ArrayList<EntitySurvey>, val context: Context) : RecyclerView.Adapter<SurveyHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyHolder {

        val inflater = LayoutInflater.from(parent.context)

        return SurveyHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {

        return  surveyList.size
    }

    override fun onBindViewHolder(holder: SurveyHolder, position: Int) {

        holder.txtvFullName.text = "${surveyList[position].nameSurvey}"
        holder.txtGender.text = if(surveyList[position].gender==1) "Masculino" else "Femenino"
        holder.email.text = if(surveyList[position].recomend==true) "Nos Recomendaria" else "No nos recomendaria"
        holder.imgUser.setImageResource(if(surveyList[position].gender==1) (R.drawable.boy) else (R.drawable.girl))
        holder.txtvDate.text = "Fecha de creacion ${surveyList[position].date}"
        holder.txtvTime.text = "Hora de creacion ${surveyList[position].time}"


        holder.btnDelete.setOnClickListener{
            miDialogo(surveyList[position].nameSurvey, it).show()
        }
        holder.btnEdit.setOnClickListener{

            val intent = Intent(context, EditActivity::class.java).apply {
                putExtra(Constants.ID, position)
            }
            ContextCompat.startActivity(context, intent, null)
        }
        holder.btnView.setOnClickListener{

            val intent = Intent(context, DetailActivity::class.java).apply{
                putExtra(Constants.ID, position)
            }
            context.startActivity(intent)
        }
    }

    private fun miDialogo(name:String, view:View) : AlertDialog
    {
        val myAlert = AlertDialog.Builder(context)
        myAlert.setTitle("Eliminar Surve")
        myAlert.setIcon(R.drawable.columpio)
        myAlert.setMessage("Desea eliminar el SURVEY seleccionado?")
        myAlert.setPositiveButton("Si"){ dialogInterface: DialogInterface, i: Int ->

            val listSurvey = ListSurvey()
            if(listSurvey.delete(name)  )
            {
                notifyDataSetChanged()
                Toast.makeText(context, "Survey Eliminado", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Snackbar.make(view, "No se elimino el survey", Snackbar.LENGTH_SHORT).show()
            }

        }
        myAlert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

        }

        return myAlert.create()
    }
}


class SurveyHolder(view:View):RecyclerView.ViewHolder(view)
{
    val binding = ItemUserBinding.bind(view)

    var imgUser = binding.imgUser
    val txtGender = binding.txtvGender
    val txtvFullName = binding.txtvFullName
    val email = binding.txtvEmail
    val btnDelete = binding.btnDelete
    val btnEdit = binding.btnEdit
    val btnView = binding.btnView
    val txtvDate = binding.txtvDate
    val txtvTime = binding.txtvTime
}