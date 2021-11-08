package com.example.flickrbrowserappfragments_optional

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class likeflickerFragment : Fragment() {
lateinit var view1:View
    lateinit var rv1: RecyclerView

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view1 =  inflater.inflate(R.layout.fragment_likeflicker, container, false)
        rv1 = view1.findViewById(R.id.rv1)
        var all = arrayListOf<Item1>()
        var itm = ItemDatabase.getInstance(requireContext()).ItemDao().getAll()
        for (i in itm){
            all.add(i)
        }
        Log.d("all","$all")

        rv1.adapter?.notifyDataSetChanged()
        rv1.adapter = MyAdapter(requireContext(),all)
        rv1.layoutManager = LinearLayoutManager(requireContext())

        return view1
    }

}