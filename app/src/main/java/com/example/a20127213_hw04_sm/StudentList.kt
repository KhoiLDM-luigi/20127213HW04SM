package com.example.a20127213_hw04_sm

import android.content.Context
import android.widget.Toast
import java.io.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.FieldPosition


class StudentList private constructor(){
    companion object {
        @Volatile
        private lateinit var instance: StudentList

        fun getInstance(): StudentList {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = StudentList()
                }
                return instance
            }
        }
    }
    private var filename: String = ""
    private var list = arrayListOf<Student>()

    fun getFilename() = filename
    fun setFilename(filename: String) {this.filename = filename}
    fun getList() = list

    fun addStudent(student: Student){
        list.add(student)
    }

    fun removeStudentAt(position: Int){
        list.removeAt(position)
    }

    fun getStudentAt(position: Int) = list[position]
    fun setStundentAt(student: Student, position: Int){
        list[position] = student
    }
    fun readFile(context: Context){
        try {
            val inputStream: InputStream? = context.openFileInput(filename)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val reader = BufferedReader(inputStreamReader)

                var line : String? = reader.readLine()
                while (line != null) {
                    var value = Json.decodeFromString<Student>(line)
                    list.add(value)
                    line = reader.readLine()

                }
            }
        } catch (e: FileNotFoundException) {
            println("<<<FileNotFound>>>")
        } catch (t: Throwable) {
            Toast.makeText(context, "Exception: $t", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveFile(context: Context){
        try {
            val file = File(context.filesDir, filename)
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            val writer = OutputStreamWriter(outputStream)
            for ((i, student) in list.withIndex()){
                var line = Json.encodeToString(student)
                if (i != list.lastIndex) line += "\n"
                writer.write(line)
            }
            writer.close()
            outputStream.close()
        } catch (t: Throwable) {
            Toast.makeText(context, "Exception: $t", Toast.LENGTH_SHORT).show()
        }
    }
}