package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MemoActivity
import com.example.myapplication.R
import com.example.myapplication.model.Memo
import java.text.SimpleDateFormat
import java.util.Date

class MemoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context=context
    private val arrayList = ArrayList<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_memo,parent,false)
        return HolderView(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item : Memo=arrayList.get(position)
        val view=holder as HolderView

        view.text_title.setText(item.title)
        view.text_memo.setText(item.memo)
        view.text_datetime.setText(item.datetime.toString())
        val date=Date()
        date.time=item.datetime.toLong()
        Log.d("hhh",date.time.toString()+"/"+item.datetime.toString() )
        view.text_datetime.setText(SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date))

        view.itemView.setOnClickListener{
            val intent= Intent(context,MemoActivity::class.java)
            intent.putExtra("item",item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    fun setList(array : ArrayList<Memo>){
        arrayList.clear()
        arrayList.addAll(array)
        notifyDataSetChanged()
    }

    private class HolderView(view: View): RecyclerView.ViewHolder(view){
        val text_title=view.findViewById<TextView>(R.id.text_title)
        val text_memo=view.findViewById<TextView>(R.id.text_memo)
        val text_datetime=view.findViewById<TextView>(R.id.text_datetime)

    }

}