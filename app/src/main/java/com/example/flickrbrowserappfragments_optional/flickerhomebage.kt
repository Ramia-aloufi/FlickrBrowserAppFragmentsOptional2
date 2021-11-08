package com.example.flickrbrowserappfragments_optional

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class flickerhomebage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var view1: View
    lateinit var ll1: LinearLayout
    lateinit var ll: LinearLayout
    lateinit var rv:RecyclerView
    lateinit var et:EditText
    lateinit var show:Button
    lateinit var imageView2:ImageView

    var search =  ""
    var url = ""
    lateinit var al:ArrayList<Item1>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_flickerhomebage, container, false)
        imageView2 = view1.findViewById(R.id.imageView2)
        show = view1.findViewById(R.id.button)
        rv = view1.findViewById(R.id.rv)
        ll1 = view1.findViewById(R.id.ll1)
        ll = view1.findViewById(R.id.ll)
        al = arrayListOf()
        et = view1.findViewById(R.id.editTextTextPersonName)
        show.setOnClickListener {
            search = et.text.toString()
            url = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=5cfbf3eb82179f031c7e1b5d82759cdb&tags=$search&format=json&nojsoncallback=1"
            requestAPI()
            rv.adapter = MyAdapter(requireContext(),al)
            rv.layoutManager = LinearLayoutManager(requireContext())
        }
        return view1
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.like,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.like-> Navigation.findNavController(view1).navigate(R.id.action_flickerhomebage_to_likeflickerFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    fun requestAPI(){
        CoroutineScope(Dispatchers.IO).launch{
            val data = async { CheckURL() }.await()

            if(data.isNotEmpty()){
                bindingToView(data)
            }
        }

    }
    fun CheckURL():String{
        var url = ""
        try {
            url = URL(this.url).readText(Charsets.UTF_8)
        }catch (e: Exception){

        }
        return url
    }

    suspend fun bindingToView(data:String){
        withContext(Dispatchers.Main){
            val jsonOpjdct = JSONObject(data)
            val photos = jsonOpjdct.getJSONObject("photos")
            val photo = photos.getJSONArray("photo")
            for (i in 0..50) {
                val title = photo.getJSONObject(i).getString("title").toString()
                val farmID = photo.getJSONObject(i).getString("farm")
                val serverID = photo.getJSONObject(i).getString("server")
                var id = photo.getJSONObject(i).getString("id")
                val secret = photo.getJSONObject(i).getString("secret")
                val img = "https://farm$farmID.staticflickr.com/$serverID/${id}_$secret.jpg"
                al.add(Item1(null,img,title,false))
                Log.d("photo", "$photo")
                imageView2.setOnClickListener { hideimg() }
            }
            rv.adapter?.notifyDataSetChanged()

        }



    }

//    fun showimg(imm:String){
//        imageView2.visibility = View.VISIBLE
//        ll1.visibility = View.VISIBLE
//        Glide.with(this).load(imm).into(imageView2)
//       this.ll.visibility = View.GONE
//        this.rv.visibility = View.GONE
//
//    }
    fun hideimg(){
        imageView2 = view1.findViewById(R.id.imageView2)
        imageView2.visibility = View.GONE
        ll1.visibility = View.GONE
        ll.visibility = View.VISIBLE
        rv.visibility = View.VISIBLE
    }

}