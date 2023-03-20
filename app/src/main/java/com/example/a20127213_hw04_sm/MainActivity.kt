package com.example.a20127213_hw04_sm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import java.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mongodb.kbson.ObjectId


class MainActivity : AppCompatActivity() {
    var manager = StudentListRealm.getInstance()

    var addStudentButton: Button? = null

    var studentListRv : RecyclerView? = null

    var changeLayoutBtn: Button? = null

    var searchBox: AutoCompleteTextView? = null

    override fun onResume(){
        super.onResume()
        searchBox?.setText("")
//        var studentListData = manager.getList()
        var studentListData = manager.getStudentList()
        var studentName = arrayListOf<String>()
        for (i in studentListData) {
            studentName.add(i.fullname)
        }

        val customAdapter = StudentListRecyclerAdapterRealm(studentListData, studentListOnClickListener?:null)
        studentListRv?.adapter = customAdapter

        var autoCompleteAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, studentName)
        searchBox?.setAdapter(autoCompleteAdapter)
    }

    var studentListOnClickListener: StudentListRecyclerAdapterRealm.OnClickListener? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_rv)

        studentListOnClickListener = object: StudentListRecyclerAdapterRealm.OnClickListener{
            override fun onClick(position: Int, model: StudentRealm) {
                var intent = Intent(this@MainActivity, StudentInfo::class.java)
//                intent.putExtra("info", Json.encodeToString(model))
//                intent.putExtra("position", position)
                intent.putExtra("id", model._id.toString())
                startActivity(intent)
            }
        }

//        manager.setFilename("student_list.txt")
//        manager.readFile(this)
//        var studentListData = manager.getList()
        var studentListData = manager.getStudentList()

        var studentName = arrayListOf<String>()
        for (i in studentListData) {
            studentName.add(i.fullname)
        }

        val customAdapter = StudentListRecyclerAdapterRealm(studentListData, studentListOnClickListener?:null)

        studentListRv = findViewById(R.id.studentListRv)
        studentListRv?.adapter = customAdapter
        studentListRv?.layoutManager = LinearLayoutManager(this)

        var autoCompleteAdapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, studentName)
        searchBox = findViewById(R.id.searchBox)
        searchBox?.setAdapter(autoCompleteAdapter)

        searchBox?.addTextChangedListener( object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var studentListData = manager.getListFilteredByName(searchBox?.text.toString())
                val customAdapter = StudentListRecyclerAdapterRealm(studentListData, studentListOnClickListener?:null)
                studentListRv?.adapter = customAdapter
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        addStudentButton = findViewById(R.id.newStudent)
        addStudentButton?.setOnClickListener{
            var intent = Intent(this, NewStudent::class.java)
            startActivity(intent)
        }

        changeLayoutBtn = findViewById(R.id.changeLayoutBtn)
        changeLayoutBtn?.setOnClickListener{
            if (studentListRv?.layoutManager is GridLayoutManager)
                studentListRv?.layoutManager = LinearLayoutManager(this)
            else
                studentListRv?.layoutManager = GridLayoutManager(this,2, RecyclerView.VERTICAL, false)
        }

    }

}