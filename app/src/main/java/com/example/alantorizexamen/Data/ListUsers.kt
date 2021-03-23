package com.example.alantorizexamen.Data

import android.util.Log
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants

class ListUsers {

    fun add(user: EntityUser):Int
    {
        var answer=-1
        if(!existNameFilter(user.name))
        {
            listUsers.add(user)
            answer = listUsers.size -1
        }

        return answer
    }

    fun delete(name: String):Boolean
    {
        var answer : Boolean = false

        for((index, item) in listUsers.withIndex())
        {
            if(item.name == name)
            {
                listUsers.removeAt(index)
                answer = true
                break
            }
        }
        return answer
    }

    fun edit(position : Int, user: EntityUser):Boolean
    {
        var answer : Boolean = false

        if(position>-1)
        {
            listUsers[position].name= user.name
            listUsers[position].phone = user.phone
            listUsers[position].gender = user.gender
            listUsers[position].email = user.email

            answer = true
        }

        return answer
    }

    fun showListUser()
    {
        Log.d(Constants.LOG_TAG, "${listUsers.size}")

        for((index, item) in listUsers.withIndex())
        {
            //Log.d(Constants.LOG_TAG)
            Log.d(Constants.LOG_TAG, "$index ${listUsers[index].name}, ${listUsers[index].email}, ${listUsers[index].gender}")
        }

    }

    fun existPasswordFilter(password :String): Boolean
    {
        var answer: Boolean = false

        if(listUsers.filter { e-> e.password == password }.count()==1)
        {
            answer=true
        }

        return answer
    }

    fun existNameFilter(name :String): Boolean
    {
        var answer: Boolean = false

        if(listUsers.filter { e-> e.name == name }.count()==1)
        {
            answer=true
        }

        return answer
    }

    fun existEmailFilter(email:String): Boolean
    {
        var answer: Boolean = false

        if(listUsers.filter { e-> e.email == email }.count()==1)
        {
            answer=true
        }

        return answer
    }


    fun getStringArray():Array<String>
    {
        val answerList = arrayListOf<String>()
        for((index, item)in listUsers.withIndex())
        {
            answerList.add("${item.name}, ${item.email}")
        }
        return answerList.toTypedArray()
    }

    fun getUser(index:Int): EntityUser
    {

        return listUsers[index]
    }

    fun getEntityUserArray():Array<EntityUser>
    {
        return listUsers.toTypedArray()
    }

    fun getListUserArray() : ArrayList<EntityUser>
    {
        return listUsers
    }

    fun setId() : Int
    {
        return listUsers.size-1
    }

    fun getId(email:String, pass:String) : Int
    {

        var id:Int=-1
        for((index, item)in listUsers.withIndex())
        {
            if(item.email==email && item.password ==pass)
            {
                id = item.id
                break
            }
        }
        return id
    }

    companion object
    {
        private var listUsers = arrayListOf<EntityUser>()
    }
}