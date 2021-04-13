package com.example.alantorizexamen.Contracts

import android.provider.BaseColumns

object SurveyContract
{
    object Entry: BaseColumns
    {
        const val TABLE_NAME = "CTL_SURVEY"
        const val  COLUMN_NAME_NAME ="Name"
        const val  COLUMN_NAME_AGE ="Age"
        const val  COLUMN_NAME_GENDER ="Gender"
        const val  COLUMN_NAME_FOOD ="Food"
        const val  COLUMN_NAME_DRINK  ="Drink"
        const val  COLUMN_NAME_ZONE ="Zone"
        const val  COLUMN_NAME_ORDER_TYPE ="OrderType"
        const val  COLUMN_SOCIAL_MEDIA ="SocialMedia"
        const val  COLUMN_APP_MOVIL ="AppMovil"
        const val  COLUMN_RECOMMEND ="Recommend"
        const val  COLUMN_DATE ="Date"
        const val  COLUMN_TIME ="Time"
        const val  COLUMN_DATE_SYSTEM ="DateSystem"
    }
}