package com.example.a20127213_hw04_sm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import java.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    var manager = StudentList.getInstance()

    var studentList : ListView? = null
    var addStudentButton: Button? = null

    override fun onResume(){
        super.onResume()
        var studentListData = manager.getList()
        var idData = arrayListOf<String>()
        for ((i, value) in studentListData.withIndex()) idData.add(i.toString())
        val customAdapter = StudentListAdapter(this, studentListData, idData)
        studentList?.adapter = customAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var a = Student("Nguyen Van a", "22/11/2002", "20KTPM1", "other")
//        var b = Student("Le Thi B", "22/11/2002", "20KTPM1", "other")
//        var c = Student("Nguyen Duong C", "22/11/2002", "20KTPM1", "other")
//
////        val studentName = arrayListOf("Nguyen Van A", "Le Thi B", "Nguyen Duong C")
////        val studentClass = arrayListOf("20KTPM1", "20KTPM2", "20KTPM3")
////        val studentDob = arrayListOf("20/11/2002", "20/21/2001", "20/22/2002")
////        val studentAvatar = arrayListOf(R.drawable.default_avatar, R.drawable.default_avatar, R.drawable.default_avatar)
//        val studentListData = arrayListOf(a, b, c)
//        val idData = arrayListOf("001", "002", "003")

        manager.setFilename("student_list.txt")
        manager.readFile(this)
        var studentListData = manager.getList()

        var idData = arrayListOf<String>()
        for ((i, value) in studentListData.withIndex()) {
            idData.add(i.toString())
        }
        val customAdapter = StudentListAdapter(this, studentListData, idData)

        studentList = findViewById(R.id.studentlist)
        studentList?.adapter = customAdapter

        addStudentButton = findViewById(R.id.newStudent)
        addStudentButton?.setOnClickListener{
            var intent = Intent(this, NewStudent::class.java)
            startActivity(intent)
        }

        studentList?.setOnItemClickListener{ parent, view, position, id ->
            var curStudent = manager.getStudentAt(position)
            var intent = Intent(this, StudentInfo::class.java)
            intent.putExtra("info", Json.encodeToString(curStudent))
            intent.putExtra("position", position)
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        manager.saveFile(this)
    }
}