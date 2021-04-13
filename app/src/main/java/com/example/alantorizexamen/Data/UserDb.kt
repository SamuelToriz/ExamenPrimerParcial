package com.example.alantorizexamen.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.alantorizexamen.Contracts.UserContract
import com.example.alantorizexamen.Entity.EntityUser
import com.example.alantorizexamen.Tools.Constants

class UserDb (val context: Context)
{
    val connectionDb = ConnectionDb(context)
    private lateinit var db: SQLiteDatabase

    fun add(user:EntityUser):Long
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues().apply {
            put(UserContract.Entry.COLUMN_NAME_NAME,user.name)
            put(UserContract.Entry.COLUMN_NAME_EMAIL,user.email)
            put(UserContract.Entry.COLUMN_NAME_PASSWORD,user.password)
            put(UserContract.Entry.COLUMN_NAME_PHONE,user.phone)
            put(UserContract.Entry.COLUMN_NAME_GENDER,user.gender)

        }

        return db.insert(UserContract.Entry.TABLE_NAME, null, values)
    }

    fun update(user:EntityUser):Int
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues().apply {
            put(UserContract.Entry.COLUMN_NAME_NAME,user.name)
            put(UserContract.Entry.COLUMN_NAME_EMAIL,user.email)
            put(UserContract.Entry.COLUMN_NAME_PASSWORD,user.password)
            put(UserContract.Entry.COLUMN_NAME_PHONE,user.phone)
            put(UserContract.Entry.COLUMN_NAME_GENDER,user.gender)
        }

        val where = "${BaseColumns._ID} =?"
        val args = arrayOf(user.id.toString())

        //regresa 0 sino lo edita 1 si lo edita
        return db.update(UserContract.Entry.TABLE_NAME, values, where, args)

    }

    fun delete(idUser:Int):Int
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val where = "${BaseColumns._ID} =?"
        val args = arrayOf(idUser.toString())

        //regresa 0 sino lo borra 1 si lo borra
        return db.delete(UserContract.Entry.TABLE_NAME,where,args)
    }

    fun getAll()
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_READ)

        val projection = arrayOf(BaseColumns._ID,    //0
                    UserContract.Entry.COLUMN_NAME_NAME, //1
                    UserContract.Entry.COLUMN_NAME_EMAIL, //2
                    UserContract.Entry.COLUMN_NAME_PASSWORD, //3
                    UserContract.Entry.COLUMN_NAME_PHONE, //4
                    UserContract.Entry.COLUMN_NAME_GENDER) //5

        val sortOrder = "${UserContract.Entry.COLUMN_NAME_NAME} DESC"

        val cursor = db.query(UserContract.Entry.TABLE_NAME,projection,null,null,null,null,sortOrder)


        if(cursor.moveToFirst())
        {
            do
            {
                //cachar los valores en variables
                //val x:Long = cursor.getLong(0)

                Log.d(Constants.LOG_TAG, "${cursor.getLong(0)}" +
                        " ${cursor.getString(1)} " +
                        " ${cursor.getString(2)} " +
                        " ${cursor.getString(3)} " +
                        " ${cursor.getString(4)}" +
                        " ${cursor.getInt(5)}")
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

    fun getOne(idUser: Int)
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_READ)

        val projection = arrayOf(BaseColumns._ID,    //0
                UserContract.Entry.COLUMN_NAME_NAME, //1
                UserContract.Entry.COLUMN_NAME_EMAIL, //2
                UserContract.Entry.COLUMN_NAME_PASSWORD, //3
                UserContract.Entry.COLUMN_NAME_PHONE, //4
                UserContract.Entry.COLUMN_NAME_GENDER) //5

        val where = "${BaseColumns._ID} =?"
        val args = arrayOf(idUser.toString())

        val cursor = db.query(UserContract.Entry.TABLE_NAME,projection,where,args,null,null,null)


        if(cursor.moveToFirst())
        {
            //cachar los valores en variables
            //val x:Long = cursor.getLong(0)

            Log.d(Constants.LOG_TAG, "${cursor.getLong(0)}" +
                    " ${cursor.getString(1)} " +
                    " ${cursor.getString(2)} " +
                    " ${cursor.getString(3)} " +
                    " ${cursor.getString(4)}" +
                    " ${cursor.getInt(5)}")

        }
        else
        {
            Log.d(Constants.LOG_TAG, "No hay datos")
        }
    }


    fun search(valueToSearch:String)
    {
        db=connectionDb.openConnection(ConnectionDb.MODE_READ)

        val projection = arrayOf(BaseColumns._ID,    //0
                UserContract.Entry.COLUMN_NAME_NAME, //1
                UserContract.Entry.COLUMN_NAME_EMAIL, //2
                UserContract.Entry.COLUMN_NAME_PASSWORD, //3
                UserContract.Entry.COLUMN_NAME_PHONE, //4
                UserContract.Entry.COLUMN_NAME_GENDER) //5

        val sortOrder = "${UserContract.Entry.COLUMN_NAME_NAME} DESC"

        val where = "${UserContract.Entry.COLUMN_NAME_NAME} LIKE?"
        val args = arrayOf("%$valueToSearch%")

        val cursor = db.query(UserContract.Entry.TABLE_NAME,projection,where,args,null,null,sortOrder)


        if(cursor.moveToFirst())
        {
            do
            {
                //cachar los valores en variables
                //val x:Long = cursor.getLong(0)

                Log.d(Constants.LOG_TAG, "${cursor.getLong(0)}" +
                        " ${cursor.getString(1)} " +
                        " ${cursor.getString(2)} " +
                        " ${cursor.getString(3)} " +
                        " ${cursor.getString(4)}" +
                        " ${cursor.getInt(5)}")
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