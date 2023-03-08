package com.example.a20127213_hw04_sm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import java.io.*

class MainActivity : AppCompatActivity() {
//    fun readFile(filename: String) : String{
//        try {
//            val inputStream: InputStream? = openFileInput(filename)
//            if (inputStream != null) {
//                val inputStreamReader = InputStreamReader(inputStream)
//                val reader = BufferedReader(inputStreamReader)
//                val stringBuffer = StringBuffer()
//                var line : String? = reader.readLine()
//                while (line != null) {
//                    stringBuffer.append(line + "\n")
//                    line = reader.readLine()
//                }
//                inputStream.close()
//                //editText.setText(stringBuffer.toString())
//                return stringBuffer.toString()
//            }
//        } catch (e: FileNotFoundException) {
//        } catch (t: Throwable) {
//            Toast.makeText(this, "Exception: $t", Toast.LENGTH_SHORT).show()
//        }
//        return ""
//    }
//
//    fun writeFile(data: String, filename: String){
//        try {
//            val file = File(filename)
//            file.createNewFile()
//            val outputStream = FileOutputStream(file)
//            val writer = OutputStreamWriter(outputStream)
//            writer.write(data)
//            writer.close()
//            outputStream.close()
//        } catch (t: Throwable) {
//            Toast.makeText(this, "Exception: $t", Toast.LENGTH_SHORT).show()
//        }
//    }

    var studentList : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentName = arrayListOf("Nguyen Van A", "Le Thi B", "Nguyen Duong C")
        val studentClass = arrayListOf("20KTPM1", "20KTPM2", "20KTPM3")
        val studentDob = arrayListOf("20/11/2002", "20/21/2001", "20/22/2002")
        val studentAvatar = arrayListOf(R.drawable.default_avatar, R.drawable.default_avatar, R.drawable.default_avatar)

        val customAdapter = StudentListAdapter(this, studentName, studentClass, studentDob, studentAvatar)

        studentList = findViewById(R.id.studentlist)
        studentList?.adapter = customAdapter

    }
}