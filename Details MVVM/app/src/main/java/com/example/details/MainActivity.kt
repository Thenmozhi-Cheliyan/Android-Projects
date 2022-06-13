package com.example.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.details.database.Details
import com.example.details.database.DetailsDao
import com.example.details.database.DetailsDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailsAdapter
    private lateinit var viewModel: DetailsViewModel
    private lateinit var viewModel1: AddDetailsViewModel
   lateinit var list: MutableList<List<Details>>
   //lateinit var list_details: ArrayList<Details>
    lateinit var database: DetailsDatabase
    lateinit var noteDao: DetailsDao

//    var callback: ItemTouchHelper.SimpleCallback =
//        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                CoroutineScope(Dispatchers.IO).launch {
//                    val list: List<Details> = noteDao.getAllDetails()
//                    withContext(Dispatchers.Main) {
//                        list.drop(viewHolder.adapterPosition)
//                        deleteNote(adapter.cId.toInt())
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//            }
//        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel1 = ViewModelProvider(this).get(AddDetailsViewModel::class.java)
        add = findViewById(R.id.add)

        database = DetailsDatabase.getInstance(this)!!
        noteDao = database!!.detailsDao()
        val list: ArrayList<Details> = arrayListOf()

//        setData()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getData().observe(this) {
            list.clear()
            list.addAll(it)
            adapter = DetailsAdapter(list, this@MainActivity)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }



        val swipeToUpdateCallback = object : SwipeToUpdateCallback() {
            //            val id = intent.getStringExtra("id")?.toLong().toString()
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main){
                            var intent = Intent(this@MainActivity, AddDetails::class.java)

                            val position = viewHolder.adapterPosition
                            Log.i("Check", "$position")
                            val note = list[position]
                            Log.i("listposition","$note")
                    intent.putExtra("data", note)
                    intent.putExtra("isUpdate",true)
                    startActivity(intent)
                            updateItem(note)
                        }

//                    val database: DetailsDatabase? =
//                        DetailsDatabase.getInstance(context = this@MainActivity)
//                    val noteDao: DetailsDao? = database?.detailsDao()
////
//                    val position = viewHolder.adapterPosition
//                    val note = list_details[position]
//
//                    var intent = Intent(this@MainActivity, AddDetails::class.java)
//                    intent.putExtra("data", note)
//                    intent.putExtra("isUpdate",true)
//                    startActivity(intent)
////                    updateNote(note)



                }

            }

        }
        val itemTouchHelperUpdate = ItemTouchHelper(swipeToUpdateCallback)
        itemTouchHelperUpdate.attachToRecyclerView(recyclerView)


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val database = DetailsDatabase.getInstance(applicationContext)
//                val noteDao = database!!.detailsDao()
                CoroutineScope(Dispatchers.IO).launch {
                    /* = java.util.ArrayList<com.example.details.database.Details> */
                 //val list : LiveData<List<Details>> = noteDao.getAllDetails()
                    withContext(Dispatchers.Main) {
                        val position = viewHolder.adapterPosition
//                        list_details.removeAt(position)
//                        adapter.setDetailList(list_details)
//                        adapter.notifyItemRemoved(position)
//                        adapter.notifyDataSetChanged()
//                        deleteNote(position)
                      // deleteNote(adapter.cId)
//                        list.removeAt(viewHolder.adapterPosition)
//                        adapter.notifyDataSetChanged()
                        removeItem(adapter.cId)


                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        add.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddDetails::class.java))
        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun setData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            list = noteDao.getAllDetails()
//            list_details =
//                list as ArrayList<Details> /* = java.util.ArrayList<com.example.details.database.Details> */
//            withContext(Dispatchers.Main) {
//                adapter = DetailsAdapter(this@MainActivity)
//                adapter.setDetailList(list)
//                recyclerView.adapter = adapter
//                adapter.notifyDataSetChanged()
////                ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
//
//            }
//        }
//    }


    fun removeItem(position : Int) {
        viewModel.deleteOne(position)
    }

    fun updateItem(details: Details){
        viewModel1.updateOne(details)
    }

//    fun updateNote(id: Details){
//        CoroutineScope(Dispatchers.Main).launch {
//            withContext(Dispatchers.IO) {
//                noteDao?.update(id)
//            }
//        }
//
//    }
}
