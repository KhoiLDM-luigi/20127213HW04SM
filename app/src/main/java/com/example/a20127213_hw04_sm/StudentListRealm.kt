package com.example.a20127213_hw04_sm

import android.content.Context
import android.widget.Toast
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmUUID
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*


class StudentListRealm {

    var realm: Realm
    private constructor(){
        val configuration = RealmConfiguration.create(setOf(StudentRealm::class))
        realm = Realm.open(configuration)

    }
    companion object {
        @Volatile
        private lateinit var instance: StudentListRealm

        fun getInstance(): StudentListRealm {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = StudentListRealm()
                }
                return instance
            }
        }
    }

    fun getListFilteredByName(data: String): List<StudentRealm>{
        var list = arrayListOf<StudentRealm>()
        var result = realm.query<StudentRealm>("fullname CONTAINS $0", data).find()
        list.addAll(result)
        return list
    }

    fun addStudent(student: StudentRealm){
        realm.writeBlocking {
            this.copyToRealm(student)
        }
    }

    fun getStudent(uuid: String): StudentRealm{
        var pk = RealmUUID.from(uuid)
        return realm.query<StudentRealm>("_id == $0", pk).find().first()
    }

    fun removeStudent(student: StudentRealm){
        realm.writeBlocking {
            val student: StudentRealm = this.query<StudentRealm>("_id == $0", student._id).find().first()
            delete(student)
        }
    }

    fun updateStudent(uuid: RealmUUID,fullname: String, dob: String, curClass: String, gender: String){
        realm.writeBlocking{
            var s: StudentRealm = realm.query<StudentRealm>("_id == $0", uuid).find().first()
            var curStudent: StudentRealm? = this.findLatest<StudentRealm>(s)
            curStudent?.fullname = fullname
            curStudent?.dob = dob
            curStudent?.curClass = curClass
            curStudent?.gender

        }
    }

    fun getStudentList(): List<StudentRealm>{
        var list = arrayListOf<StudentRealm>()
        var result = realm.query<StudentRealm>().find()
        list.addAll(result)
        return list
    }

    fun closeRealm(){
        realm.close()
    }
}