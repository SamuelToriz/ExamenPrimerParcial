package com.example.alantorizexamen.Data

import android.util.Log
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Tools.Constants

class ListSurvey {
    fun add(survey: EntitySurvey):Int
    {
        var answer=-1
        if(!existNameFilter(survey.nameSurvey))
        {
            listSurvey.add(survey)
            answer = listSurvey.size -1
        }

        return answer
    }

    fun delete(name: String):Boolean
    {
        var answer : Boolean = false

        for((index, item) in listSurvey.withIndex())
        {
            if(item.nameSurvey == name)
            {
                listSurvey.removeAt(index)
                answer = true
                break
            }
        }
        return answer
    }

    fun edit(position : Int, survey : EntitySurvey):Boolean
    {
        var answer : Boolean = false

        if(position>-1)
        {
            listSurvey[position].nameSurvey= survey.nameSurvey
            listSurvey[position].food = survey.food
            listSurvey[position].cerveza = survey.cerveza
            listSurvey[position].malteada = survey.malteada
            listSurvey[position].refresco = survey.refresco
            listSurvey[position].gender = survey.gender

            answer = true
        }

        return answer
    }

    fun showListSurveys()
    {
        Log.d(Constants.LOG_TAG, "${listSurvey.size}")

        for((index, item) in listSurvey.withIndex())
        {
            //Log.d(Constants.LOG_TAG)
            Log.d(Constants.LOG_TAG, "$index ${listSurvey[index].nameSurvey}")
        }

    }

    fun existNameFilter(name:String): Boolean
    {
        var answer: Boolean = false

        if(listSurvey.filter { e-> e.nameSurvey == name }.count()==1)
        {
            answer=true
        }

        return answer
    }

    fun existIdFilter(id : Int): ArrayList<EntitySurvey>
    {

        if(listSurvey.filter { e-> e.id == id }.count()==1)
        {
           return listSurvey
        }
        return listSurvey
    }

    fun existEmailFilter(name:String): Boolean
    {
        var answer: Boolean = false

        if(listSurvey.filter { e-> e.nameSurvey == name }.count()==1)
        {
            answer=true
        }

        return answer
    }


    fun getStringArray(id:Int):Array<String>
    {
        val answerList = arrayListOf<String>()
        for((index, item)in listSurvey.withIndex())
        {
            if(item.id==id)
            {
                answerList.add("${item.nameSurvey}")
            }
        }
        return answerList.toTypedArray()
    }

    fun getSurvey(index:Int):EntitySurvey
    {

        return listSurvey[index]
    }

    fun getEntitySurveyArray():Array<EntitySurvey>
    {
        return listSurvey.toTypedArray()
    }

    fun getListSurveyArray(id:Int) : ArrayList<EntitySurvey>
    {
        var listSurveyFiltered = arrayListOf<EntitySurvey>()
        for((i, item) in listSurvey.withIndex())
        {
            if(item.id==id)
            {
                listSurveyFiltered.add(item)
            }
        }
        return listSurveyFiltered
    }

    companion object
    {
        private var listSurvey = arrayListOf<EntitySurvey>()
    }
}