package com.example.jul_allim

data class Student( val name: String = "Name",
                    val id: String = "ID",
                    val session:String = "Session",
                    val line : String = "Line"


)

data class User (  val uname: String ?=null,
                    val uid: String ?= null,
                   val usession: String ?= null,
                   val uline: String ?= null

)