package com.example.a20127213_hw04_sm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class StudentInfo : AppCompatActivity() {

    var manager = StudentListRealm.getInstance()
    var studentName: EditText? = null
    var studentDob: EditText? = null
    var studentClass: Spinner? = null
    var studentGender: RadioGroup? = null

    var saveBtn: Button? = null
    var deleteBtn: Button? = null

    var student = StudentRealm()
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        var uuid = intent.getStringExtra("id").toString()
        student = manager.getStudent(uuid)
        position = intent.getIntExtra("position", 0)

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
            var fullname = studentName?.text.toString()
            var dob = studentDob?.text.toString()
            var curClass= studentClass?.selectedItem.toString()
            val genderChecked = studentGender?.checkedRadioButtonId
            var gender = ""
            when(genderChecked){
                R.id.female -> gender = "female"
                R.id.male -> gender = "male"
                R.id.other -> gender = "other"
            }
//            manager.setStundentAt(student, position)
            manager.updateStudent(student._id, fullname, dob, curClass, gender)
            intent.data = null
            finish()
        }

        deleteBtn = findViewById(R.id.deleteBtn)
        deleteBtn?.setOnClickListener{
            manager.removeStudent(student)
            intent.data = null
            finish()
        }
    }
}