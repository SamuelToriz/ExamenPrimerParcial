package com.example.alantorizexamen.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.alantorizexamen.Contracts.SurveyContract
import com.example.alantorizexamen.Contracts.UserContract
import com.example.alantorizexamen.Entity.EntitySurvey
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants

class SurveyDb (val context: Context)
{
    val connectionDb = ConnectionDb(context)
    private lateinit var db: SQLiteDatabase

    fun add(survey: EntitySurvey):Long
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues().apply {
            put(SurveyContract.Entry.COLUMN_NAME_NAME,survey.nameSurvey)
            put(SurveyContract.Entry.COLUMN_NAME_AGE,survey.age)
            put(SurveyContract.Entry.COLUMN_NAME_GENDER,survey.gender)
            put(SurveyContract.Entry.COLUMN_NAME_FOOD,survey.food)
            put(SurveyContract.Entry.COLUMN_NAME_DRINK,survey.cerveza)
            put(SurveyContract.Entry.COLUMN_NAME_ZONE,survey.Zone)
            put(SurveyContract.Entry.COLUMN_NAME_ORDER_TYPE,survey.typeOrder)
            put(SurveyContract.Entry.COLUMN_SOCIAL_MEDIA,survey.Instagram)
            put(SurveyContract.Entry.COLUMN_APP_MOVIL,survey.appMovil)
            put(SurveyContract.Entry.COLUMN_RECOMMEND,survey.recomend)
            put(SurveyContract.Entry.COLUMN_DATE,survey.date)
            put(SurveyContract.Entry.COLUMN_TIME,survey.time)

        }

        return db.insert(UserContract.Entry.TABLE_NAME, null, values)
    }

    fun update(survey:EntitySurvey):Int
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues().apply {
            put(SurveyContract.Entry.COLUMN_NAME_NAME,survey.nameSurvey)
            put(SurveyContract.Entry.COLUMN_NAME_AGE,survey.age)
            put(SurveyContract.Entry.COLUMN_NAME_GENDER,survey.gender)
            put(SurveyContract.Entry.COLUMN_NAME_FOOD,survey.food)
            put(SurveyContract.Entry.COLUMN_NAME_DRINK,survey.cerveza)
            put(SurveyContract.Entry.COLUMN_NAME_ZONE,survey.Zone)
            put(SurveyContract.Entry.COLUMN_NAME_ORDER_TYPE,survey.typeOrder)
            put(SurveyContract.Entry.COLUMN_SOCIAL_MEDIA,survey.Instagram)
            put(SurveyContract.Entry.COLUMN_APP_MOVIL,survey.appMovil)
            put(SurveyContract.Entry.COLUMN_RECOMMEND,survey.recomend)
            put(SurveyContract.Entry.COLUMN_DATE,survey.date)
            put(SurveyContract.Entry.COLUMN_TIME,survey.time)
        }

        val where = "${BaseColumns._ID} =?"
        val args = arrayOf(survey.id.toString())

        //regresa 0 sino lo edita 1 si lo edita
        return db.update(SurveyContract.Entry.TABLE_NAME, values, where, args)

    }

    fun delete(idSurvey:Int):Int
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val where = "${BaseColumns._ID} =?"
        val args = arrayOf(idSurvey.toString())

        //regresa 0 sino lo borra 1 si lo borra
        return db.delete(UserContract.Entry.TABLE_NAME,where,args)
    }

    fun getAll()
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_READ)

        val projection = arrayOf(BaseColumns._ID,    //0
                SurveyContract.Entry.COLUMN_NAME_NAME, //1
                SurveyContract.Entry.COLUMN_NAME_AGE, //2
                SurveyContract.Entry.COLUMN_NAME_GENDER, //3
                SurveyContract.Entry.COLUMN_NAME_FOOD, //4
                SurveyContract.Entry.COLUMN_NAME_DRINK, //5
                SurveyContract.Entry.COLUMN_NAME_ZONE, //6
                SurveyContract.Entry.COLUMN_NAME_ORDER_TYPE, //7
                SurveyContract.Entry.COLUMN_SOCIAL_MEDIA, //8
                SurveyContract.Entry.COLUMN_APP_MOVIL, //9
                SurveyContract.Entry.COLUMN_RECOMMEND, //10
                SurveyContract.Entry.COLUMN_DATE, //11
                SurveyContract.Entry.COLUMN_TIME) //12



        val sortOrder = "${SurveyContract.Entry.COLUMN_NAME_NAME} DESC"

        val cursor = db.query(SurveyContract.Entry.TABLE_NAME,projection,null,null,null,null,sortOrder)


        if(cursor.moveToFirst())
        {
            do
            {
                //cachar los valores en variables
                //val x:Long = cursor.getLong(0)

                Log.d(Constants.LOG_TAG, "${cursor.getLong(0)}" +
                        " ${cursor.getString(1)} " +
                        " ${cursor.getInt(2)} " +
                        " ${cursor.getInt(3)} " +
                        " ${cursor.getString(4)}" +
                        " ${cursor.getString(5)}" +
                        " ${cursor.getString(6)}" +
                        " ${cursor.getString(7)}" +
                        " ${cursor.getString(8)}" +
                        " ${cursor.getString(9)}" +
                        " ${cursor.getString(10)}" +
                        " ${cursor.getString(11)}" +
                        " ${cursor.getString(12)}" )
            }
            //Si hay mas que mostrar
            //cursor.moveToNext()
            while (cursor.moveToNext())
        }
        else
        {
            Log.d(Constants.LOG_TAG, "No hay datos")
        }
    }
}