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
    var recomend : Boolean)

{
    constructor():this(0,"", 0,"", false, false, false,  false)
}