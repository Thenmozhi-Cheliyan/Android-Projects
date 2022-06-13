package com.example.details

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.details.database.Details
import android.content.Intent as Intent

class DetailsAdapter(private var list:List<Details>,val activity: Activity ):
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    //var list: List<Details> = arrayListOf()
    var cId:Int = 0


    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var mail: TextView = view.findViewById(R.id.mailID)
        var img:ImageView = view.findViewById(R.id.imageView3)
    }

    fun setDetailList(list_: List<Details>) {
        list = list_
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_item, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val details = list[position]
        holder.name.text = details.name
        holder.mail.text = details.mailID
        holder.img.setImageResource(R.drawable.contacts_icon)
        val id = details.detailsId
        cId=id
        holder.itemView.setOnClickListener {
            Log.i("Adapter","$id")
            val intent = Intent(activity, DetailsActivity::class.java)
                .putExtra("id", "$id")
            activity.startActivity(intent)



        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}