package com.example.alantorizexamen.Entity

class EntityUser (
    var id : Int,
    var name:String,
    var phone : String,
    var email : String,
    var password : String,
    var gender : Int)

{
    constructor():this(0,"", "", "","",0)
}