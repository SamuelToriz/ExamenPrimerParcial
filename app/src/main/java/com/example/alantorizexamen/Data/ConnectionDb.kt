package com.example.alantorizexamen.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.alantorizexamen.Contracts.SurveyContract
import com.example.alantorizexamen.Contracts.UserContract

class ConnectionDb(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        db?.execSQL(CREATE_TABLE_SURVEY)
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL(DROP_TABLE_SURVEY)
        onCreate(db)
        db?.execSQL(DROP_TABLE_USER)
        onCreate(db)
    }

    fun openConnection(typeConnection:Int):SQLiteDatabase
    {
        return when(typeConnection)
        {
            MODE_WRITE->{
                writableDatabase
            }
            MODE_READ->{
                readableDatabase
            }
            else -> {
                readableDatabase
            }
        }
    }

    companion object
    {
        const val DATABASE_NAME = "SURVEY DB"
        const val DATABASE_VERSION = 1
        const val CREATE_TABLE_SURVEY = "CREATE TABLE ${SurveyContract.Entry.TABLE_NAME} " +
                " (${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${SurveyContract.Entry.COLUMN_NAME_NAME} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_NAME_AGE} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_NAME_GENDER} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_NAME_FOOD} VARCHAR(20), " +
                " ${SurveyContract.Entry.COLUMN_NAME_DRINK} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_NAME_ZONE} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_NAME_ORDER_TYPE} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_SOCIAL_MEDIA} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_APP_MOVIL} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_RECOMMEND} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_DATE} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_TIME} VARHCAR(20), " +
                " ${SurveyContract.Entry.COLUMN_DATE_SYSTEM} DATE DEFAULT CURRENT_DATE)"

        const val DROP_TABLE_SURVEY = "DROP TABLE IF EXISTS ${SurveyContract.Entry.TABLE_NAME}"


        const val DATABASE_NAME_USER = "USER DB"
        const val DATABASE_VERSION_USER = 1
        const val CREATE_TABLE_USER = "CREATE TABLE ${UserContract.Entry.TABLE_NAME} " +
                " (${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${UserContract.Entry.COLUMN_NAME_NAME} VARHCAR(20), " +
                " ${UserContract.Entry.COLUMN_NAME_EMAIL} INTEGER, " +
                " ${UserContract.Entry.COLUMN_NAME_PASSWORD} INTEGER, " +
                " ${UserContract.Entry.COLUMN_NAME_PHONE} VARCHAR(20), " +
                " ${UserContract.Entry.COLUMN_NAME_GENDER} VARHCAR(20), " +
                " ${UserContract.Entry.COLUMN_DATE_SYSTEM} DATE DEFAULT CURRENT_DATE)"

        const val DROP_TABLE_USER = "DROP TABLE IF EXISTS ${UserContract.Entry.TABLE_NAME}"


        const val MODE_WRITE=1
        const val MODE_READ=2
    }
}