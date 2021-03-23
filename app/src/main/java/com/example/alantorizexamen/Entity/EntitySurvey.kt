package com.example.alantorizexamen.Entity

import java.util.*

class EntitySurvey (
    var id : Int,
    var nameSurvey:String,
    var gender : Int,
    var food : String,
    var cerveza : Boolean,
    var refresco : Boolean,
    var malteada : Boolean,
    var recomend : Boolean,
    var age : String,
    var Zone : Int,
    var typeOrder : Int,
    var facebook:Boolean,
    var Instagram:Boolean,
    var appMovil:Boolean)

{
    constructor():this(0,"", 0,"", false, false, false,
            false,"",0,0,false,false,false)
}