package com.example.a20127213_hw04_sm

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    var fullname: String,
    var dob: String,
    var curClass: String,
    var gender:String)