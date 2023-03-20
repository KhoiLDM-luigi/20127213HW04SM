package com.example.a20127213_hw04_sm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentListRecyclerAdapter(private val data: List<Student>, private var onClickListener: OnClickListener?) : RecyclerView.Adapter<StudentListRecyclerAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentName: TextView?
        val studentClass: TextView?
        val studentDobGender: TextView?
        val studentAvatar: ImageView?


        init {
            // Define click listener for the ViewHolder's View
            studentName = view.findViewById(R.id.studentName)
            studentClass = view.findViewById(R.id.studentInfoClass)
            studentDobGender = view.findViewById(R.id.studentDob)
            studentAvatar = view.findViewById(R.id.studentAvatar)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentName?.text = data[position].fullname
        holder.studentClass?.text = data[position].curClass
        holder.studentDobGender?.text = "${data[position].dob} - ${data[position].gender}"
        holder.studentAvatar?.setImageResource(R.drawable.default_avatar)
        holder.itemView.setOnClickListener{
            if (this.onClickListener != null) {
                this.onClickListener!!.onClick(position, data[position])
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

     fun filter(data: String){

     }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Student)
    }


    override fun getItemCount() = data.size
}