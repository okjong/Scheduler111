package com.example.myapplication.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DB.DBLoader
import com.example.myapplication.MemoActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.MemoAdapter
import com.example.myapplication.databinding.FragmentCalendarBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class CalendarFragment : Fragment() {

    private lateinit var adapter : MemoAdapter
    private var selectday=""

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendarView=view.findViewById<CalendarView>(R.id.calendar_view)
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        adapter= MemoAdapter(requireContext())
        recyclerView.adapter=adapter

        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener{
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                if (selectday.equals(String.format("%04d%02d%02d",p1,p2+1,p3))){
                    startActivity(Intent(requireContext(),MemoActivity::class.java))
                }else{
                    val calendar=Calendar.getInstance()
                    calendar.set(p1,p2,p3)
                    val day=calendar.timeInMillis.toString().substring(0,6)
                    adapter.setList(DBLoader(requireContext()).memoList(day.toLong()))
                    selectday = String.format("%04d%02d%02d",p1,p2+1,p3)
                    Log.d("hhh", selectday)
                }

            }

        })
        val date=Date()
        date.time = calendarView.date
        selectday = SimpleDateFormat("yyyyMMdd").format(date)
        Log.d("hhh", selectday)
        adapter.setList(DBLoader(requireContext()).memoList(calendarView.date.toString().substring(0,6).toLong()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}