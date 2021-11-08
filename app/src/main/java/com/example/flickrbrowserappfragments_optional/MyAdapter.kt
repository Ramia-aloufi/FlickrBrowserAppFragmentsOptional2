package com.example.flickrbrowserappfragments_optional

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.filckercell.view.*
import kotlinx.android.synthetic.main.filckercell.view.llo
import kotlinx.android.synthetic.main.fragment_flickerhomebage.view.*

class MyAdapter(val context1: Context, var item:ArrayList<Item1>):RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
   val fragment = flickerhomebage()
    class ItemViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.filckercell,parent,false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var items = item[position]
        holder.itemView.apply {
                textView.text = items.text
            if (items.ischecked){
                imageView3.setBackgroundResource(R.drawable.heart)
            }else{
                imageView3.setBackgroundResource(R.drawable.non)
            }
                Glide.with(context1)
                    .load(items.img)
                    .into(imageView)

//                llo.setOnClickListener { fragment.showimg(items.img) }

            imageView3.setOnClickListener {
                if(!items.ischecked) {
                    items.ischecked = true
                    imageView3.setBackgroundResource(R.drawable.heart)
                    ItemDatabase.getInstance(context1).ItemDao().UpdateItem(items)
                    ItemDatabase.getInstance(context1).ItemDao().insertItem(items)
                    notifyDataSetChanged()
                }else{
                    items.ischecked = false
                    imageView3.setBackgroundResource(R.drawable.non)
                    ItemDatabase.getInstance(context1).ItemDao().UpdateItem(items)
                    ItemDatabase.getInstance(context1).ItemDao().DeleteItem(items)
                    notifyDataSetChanged()
                }
                Log.d("likeditem","$items")
            }
        }
}

    override fun getItemCount(): Int = item.size
}