package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DB.DBLoader
import com.example.myapplication.MemoActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.MemoAdapter
import com.example.myapplication.databinding.FragmentMemoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MemoFragment : Fragment() {

    private lateinit var adapter: MemoAdapter

    private var _binding: FragmentMemoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAdd= view.findViewById<FloatingActionButton>(R.id.btn_add)
        btnAdd.setOnClickListener{
            startActivity(Intent(requireContext(),MemoActivity::class.java))
        }

        adapter= MemoAdapter(requireContext())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter=adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.setList(DBLoader(requireContext()).memoList(null))
    }

    fun delete(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}