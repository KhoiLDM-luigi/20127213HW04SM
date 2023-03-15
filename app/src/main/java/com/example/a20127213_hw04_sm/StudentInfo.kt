package com.example.a20127213_hw04_sm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class StudentInfo : AppCompatActivity() {

    var manager = StudentList.getInstance()
    var studentName: EditText? = null
    var studentDob: EditText? = null
    var studentClass: Spinner? = null
    var studentGender: RadioGroup? = null

    var saveBtn: Button? = null
    var deleteBtn: Button? = null

    var student = Student("","","","")
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        var jsonStr = intent.getStringExtra("info").toString()
        student = Json.decodeFromString<Student>(jsonStr)
        position = intent.getIntExtra("position", 0)
        println("<<<<<$position>>>>>>")
        studentName = findViewById(R.id.studentInfoName)
        studentName?.setText(student.fullname)

        studentDob = findViewById(R.id.studentInfoDob)
        studentDob?.setText(student.dob)
        studentClass = findViewById(R.id.studentInfoClass)
        studentGender = findViewById(R.id.studentInfoGender)

        ArrayAdapter.createFromResource(
            this,
            R.array.available_class,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            studentClass?.adapter = adapter
        }

        when (student.curClass){
            "20KTPM1" -> studentClass?.setSelection(0)
            "20KTPM2" -> studentClass?.setSelection(1)
            "20KTPM3" -> studentClass?.setSelection(2)
        }

        when (student.gender){
            "male" -> studentGender?.findViewById<RadioButton>(R.id.male)?.isChecked = true
            "female" -> studentGender?.findViewById<RadioButton>(R.id.female)?.isChecked = true
            "other" -> studentGender?.findViewById<RadioButton>(R.id.other)?.isChecked = true
        }


        saveBtn = findViewById(R.id.saveBtn)
        saveBtn?.setOnClickListener{
            student.fullname = studentName?.text.toString()
            student.dob = studentDob?.text.toString()
            student.curClass= studentClass?.selectedItem.toString()
            val genderChecked = studentGender?.checkedRadioButtonId
            when(genderChecked){
                R.id.female -> student.gender = "female"
                R.id.male -> student.gender = "male"
                R.id.other -> student.gender = "other"
            }
            manager.setStundentAt(student, position)
            intent.data = null
            finish()
        }

        deleteBtn = findViewById(R.id.deleteBtn)
        deleteBtn?.setOnClickListener{
            manager.removeStudentAt(position)
            intent.data = null
            finish()
        }
    }
}