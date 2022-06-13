package com.example.details

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.details.database.Details
import com.example.details.database.DetailsDatabase
import kotlinx.coroutines.*

class DetailsActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var empId: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val  id = intent.getStringExtra("id")!!.toInt()
        Log.i("DetailsActivity","${id}")
        name = findViewById(R.id.nameShow)
        email = findViewById(R.id.mailIDShow)
        empId = findViewById(R.id.employeeIDShow)
        var imageView:ImageView = findViewById(R.id.image)
        imageView.setImageResource(R.drawable.contacts_icon)

        CoroutineScope(Dispatchers.IO).launch {
            val database = DetailsDatabase.getInstance(this@DetailsActivity)
            val detailsdao = database!!.detailsDao()
            val details: Details = detailsdao.selectDetails( id)
            withContext(Dispatchers.Main) {
                name.text = details.name.toString()
                email.text = details.mailID.toString()
                empId.text = details.employeeID.toString()
            }

        }


    }
}