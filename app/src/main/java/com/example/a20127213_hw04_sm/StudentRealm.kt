package com.example.a20127213_hw04_sm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId


class StudentRealm: RealmObject {
    @PrimaryKey
    var _id = RealmUUID.random()
    var fullname : String = ""
    var dob : String = "01/01/2000"
    var curClass : String = "20KTPM1"
    var gender :String = "other"
    constructor() {}
    constructor(fullname: String, dob: String, curClass: String, gender: String){
        this.fullname = fullname
        this.dob = dob
        this.curClass = curClass
        this.gender = gender
    }
}