package com.example.myapplication.DB

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.myapplication.model.Memo
import java.util.Calendar
import java.util.Date

class DBLoader(context: Context) {

    private val context = context
    private var db:DBHelper

    init {
        db= DBHelper(context)
    }

    fun save(title:String,memo:String){
        val calendar=Calendar.getInstance()
        val contentvValues=ContentValues()
        contentvValues.put("title", title)
        contentvValues.put("memo", memo)
        contentvValues.put("datetime",calendar.timeInMillis)
        db.writableDatabase.insert("note",null,contentvValues)
        db.close()
        Toast.makeText(context, "save", Toast.LENGTH_SHORT).show()
    }

    fun delete(id:Int){
        db.writableDatabase.delete("note","id=?", arrayOf(id.toString()))
        db.close()
        Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()

    }


    fun memoList(datetime: Long?)  : ArrayList<Memo>{
        val array= ArrayList<Memo>()
        var sql=""
        if (datetime==null){
            sql="select * from note order by datetime desc"
        }else{
            sql="select * from note where datetime like '%"+datetime+ "%' order by datetime desc"
        }
        val cursor = db.readableDatabase.rawQuery(sql,null)
        while (cursor.moveToNext()){
            val id : Int= cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val title : String = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val memo :String = cursor.getString(cursor.getColumnIndexOrThrow("memo"))
            val dateTime :Long=cursor.getLong(cursor.getColumnIndexOrThrow("datetime"))
            val memoItem= Memo(id,title,memo,dateTime)
            array.add(memoItem)
        }

        return array

    }

}