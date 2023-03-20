package com.example.a20127213_hw04_sm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class NewStudent : AppCompatActivity() {

        var manager = StudentListRealm.getInstance()
        var studentName: EditText? = null
        var studentDob: EditText? = null
        var studentClass: Spinner? = null
        var studentGender: RadioGroup? = null

        var saveBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        studentName = findViewById(R.id.studentInfoName)
        studentDob = findViewById(R.id.studentInfoDob)
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

        saveBtn = findViewById(R.id.saveBtn)
        saveBtn?.setOnClickListener{
            var sname = studentName?.text.toString()
            var sdob = studentDob?.text.toString()
            var sclass = studentClass?.selectedItem.toString()
            var sgender = "other"
            val genderChecked = studentGender?.checkedRadioButtonId
            when(genderChecked){
                R.id.female -> sgender = "female"
                R.id.male -> sgender = "male"
            }

            val nStudent = StudentRealm(sname, sdob, sclass, sgender)
            manager.addStudent(nStudent)
            finish()
        }
    }
}