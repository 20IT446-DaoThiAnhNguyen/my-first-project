package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "student.db"
        private const val TBL_STUDENT = "tbl_student"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent= ("CREATE TABLE " + TBL_STUDENT + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + EMAIL + " TEXT" + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL( "DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(p0)
    }
    fun insertStudent(std: StudentModel): Long{
        val p0 = this.writableDatabase

        val contenValues = ContentValues()
        contenValues.put(ID, std.id)
        contenValues.put(NAME, std.name)
        contenValues.put(EMAIL, std.email)

        val succes = p0.insert(TBL_STUDENT, null, contenValues)
        p0.close()
        return succes
    }

    fun getAllStudent(): ArrayList<StudentModel>{
        val stdList: ArrayList<StudentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val p0 = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = p0.rawQuery(selectQuery, null)

        }catch (e: Exception){
            e.printStackTrace()
            p0.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val std = StudentModel(id = id, name = name, email = email)
                stdList.add(std)

            }while (cursor.moveToNext())
            }
        return stdList

    }
}