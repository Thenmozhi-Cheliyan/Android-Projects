package com.example.details

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.details.database.Details
import com.example.details.database.DetailsDao
import com.example.details.database.DetailsDatabase
import kotlinx.coroutines.*

class AddDetails : AppCompatActivity() {
    private  var id: Int = 0
    private lateinit var save: Button
    private lateinit var name: EditText
    private lateinit var employeeID: EditText
    private lateinit var mailID: EditText
    private lateinit var nameText:  String
    private lateinit var numberText: String
    private lateinit var mailText: String
    private lateinit var addImage: ImageView
    private var  isUpdate = false
    lateinit var viewModel: AddDetailsViewModel

    private var noteDao: DetailsDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details)
//ViewModel
        viewModel = ViewModelProvider(this)[AddDetailsViewModel::class.java]
//
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        val database: DetailsDatabase? = DetailsDatabase.getInstance(context = this)
        noteDao = database?.detailsDao()

        name = findViewById(R.id.name)
        employeeID = findViewById(R.id.employeeID)
        mailID = findViewById(R.id.mailID)
        addImage = findViewById(R.id.addDetailsImage)
        addImage.setImageResource(R.drawable.contacts_icon)

        save = findViewById(R.id.save)

        val bundle = intent.extras

        if (bundle != null) {
            if (bundle.getParcelable<Details>("data") != null) {
                val data = bundle.getParcelable<Details>("data")
                isUpdate = bundle.getBoolean("isUpdate")
                if (data != null) {
                    id = data.detailsId
                    name.setText(data.name)
                    employeeID.setText(data.employeeID)
                    mailID.setText(data.mailID)
                }


            }
        }
        save.setOnClickListener {
            val note =
                Details(0, name.text.toString(), employeeID.text.toString(), mailID.text.toString())

            CoroutineScope(Dispatchers.IO).launch {
                if (name.text.toString().trim().isNotBlank() or employeeID.text.toString().trim()
                        .isNotBlank() or mailID.text.toString().trim().isNotBlank()
                ) {
                    if (isUpdate) {
                        val details = Details(id, name.text.toString(), employeeID.text.toString(), mailID.text.toString())
                            // noteDao?.update(details)
                           // viewModel.updateOne(id)
                            viewModel.updateOne(details)

                    } else {
                       // noteDao?.insert(note)
                        //viewModel
                        viewModel.saveDetails(name,employeeID,mailID)

                    }
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddDetails, "Note saved", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

         fun updateNote(id: String) {
            Log.i("AddDetails", "$id")
             CoroutineScope(Dispatchers.IO).launch {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddDetails, "Note saved", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
