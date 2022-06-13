package com.example.details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var list: List<Details>
    lateinit var list_details: ArrayList<Details>
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
        add = findViewById(R.id.add)

        database = DetailsDatabase.getInstance(this)!!
        noteDao = database!!.detailsDao()
        setData()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


        val swipeToUpdateCallback = object : SwipeToUpdateCallback() {
            //            val id = intent.getStringExtra("id")?.toLong().toString()
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                CoroutineScope(Dispatchers.IO).launch {


                    val database: DetailsDatabase? =
                        DetailsDatabase.getInstance(context = this@MainActivity)
                    val noteDao: DetailsDao? = database?.detailsDao()
//
                    val position = viewHolder.adapterPosition
                    val note = list_details[position]

                    var intent = Intent(this@MainActivity, AddDetails::class.java)
                    intent.putExtra("data", note)
                    intent.putExtra("isUpdate",true)
                    startActivity(intent)
//                    updateNote(note)



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
//                    val list: ArrayList<Details> = noteDao.getAllDetails() as ArrayList<Details> /* = java.util.ArrayList<com.example.details.database.Details> */

                    withContext(Dispatchers.Main) {
                        val position = viewHolder.adapterPosition
                        list_details.removeAt(position)
                        adapter.setDetailList(list_details)
                        adapter.notifyItemRemoved(position)
                        adapter.notifyDataSetChanged()
//                        deleteNote(position)
                       deleteNote(adapter.cId)

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

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        CoroutineScope(Dispatchers.IO).launch {
            list = noteDao.getAllDetails()
            list_details =
                list as ArrayList<Details> /* = java.util.ArrayList<com.example.details.database.Details> */
            withContext(Dispatchers.Main) {
                adapter = DetailsAdapter(this@MainActivity)
                adapter.setDetailList(list)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
//                ItemTouchHelper(callback).attachToRecyclerView(recyclerView)

            }
        }
    }

    fun deleteNote(id: Int) {
//        val database: DetailsDatabase? = DetailsDatabase.getInstance(context = this)
//        val noteDao: DetailsDao? = database?.detailsDao()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                noteDao?.deleteDetail(id)
            }
        }
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
