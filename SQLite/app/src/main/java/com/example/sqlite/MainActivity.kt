package com.example.sqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edEmail: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        sqliteHelper = SQLiteHelper(this)

        btnAdd.setOnClickListener { addStudent() }
        btnView.setOnClickListener { getStudent() }
    }
    private fun getStudent() {
        val stdList = sqliteHelper.getAllStudent()
        Log.e("ppp", "${stdList.size}")

        //Ok Now we need to display data in RecyclerView
        adapter?.addItems(stdList)

    }

    private fun addStudent(){
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Plaese enter requied field", Toast.LENGTH_SHORT).show()
        }else {
            val std = StudentModel(name = name, email = email)
            val status = sqliteHelper.insertStudent(std)

            if(status > -1) {
                Toast.makeText(this, "Student Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter
    }
    private fun initView() {
        edName = findViewById(R.id.edName)
        edEmail = findViewById(R.id.edEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        recyclerView = findViewById(R.id.recyclerView)
    }
}