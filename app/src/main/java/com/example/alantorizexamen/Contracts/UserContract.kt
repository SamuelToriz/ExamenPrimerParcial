package com.example.alantorizexamen.Contracts

import android.provider.BaseColumns

object UserContract
{
    object Entry: BaseColumns
    {
        const val TABLE_NAME = "CTL_USER"
        const val  COLUMN_NAME_NAME ="Name"
        const val  COLUMN_NAME_EMAIL ="Email"
        const val  COLUMN_NAME_PASSWORD ="Password"
        const val  COLUMN_NAME_PHONE="Phone"
        const val  COLUMN_NAME_GENDER="Gender"
        const val  COLUMN_DATE_SYSTEM ="DateSystem"
    }
}