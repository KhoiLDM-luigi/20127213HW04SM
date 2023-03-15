package com.example.a20127213_hw04_sm

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentListAdapter (
    private val context: Activity,
    private val studentList: List<Student>,
    private val id: List<String>
) : ArrayAdapter<String>(context, R.layout.student_list_item, id) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.student_list_item, null, true)
        val nameText = rowView.findViewById(R.id.studentName) as TextView
        val imageView: ImageView = rowView.findViewById(R.id.studentAvatar) as ImageView
        val classText = rowView.findViewById(R.id.studentInfoClass) as TextView
        val dobText = rowView.findViewById(R.id.studentDob) as TextView
        nameText.text = studentList[position].fullname
        imageView.setImageResource(R.drawable.default_avatar)
        classText.text = studentList[position].curClass
        dobText.text = studentList[position].dob + " - " + studentList[position].gender
        return rowView
    }
}