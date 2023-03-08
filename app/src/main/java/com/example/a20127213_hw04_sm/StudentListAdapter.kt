package com.example.a20127213_hw04_sm

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentListAdapter (
    private val context: Activity,
    private val studentName: List<String>,
    private val studentClass: List<String>,
    private val studentDobAndGender : List<String>,
    private val studentAvatar: List<Int>
) : ArrayAdapter<String>(context, R.layout.student_list_item, studentName) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.student_list_item, null, true)
        val nameText = rowView.findViewById(R.id.studentName) as TextView
        val imageView: ImageView = rowView.findViewById(R.id.studentAvatar) as ImageView
        val classText = rowView.findViewById(R.id.studentClass) as TextView
        val dobText = rowView.findViewById(R.id.studentDob) as TextView
        nameText.text = studentName[position]
        imageView.setImageResource(studentAvatar[position])
        classText.text = studentClass[position]
        dobText.text = studentDobAndGender[position]
        return rowView
    }
}