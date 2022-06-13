package com.example.details

import android.app.Application
import android.icu.text.Transliterator
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import com.example.details.database.Details
import com.example.details.database.DetailsDao
import com.example.details.database.DetailsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDetailsViewModel(application: Application) : AndroidViewModel(application) {
    var detailsdatabase = DetailsDatabase.getInstance(application)
    var detailsDao: DetailsDao = detailsdatabase!!.detailsDao()

    fun saveDetails(personName: EditText, personID: EditText, personMailID: EditText) {

        val details = Details(
            0,
            personName.text.toString(),
            personID.text.toString(),
            personMailID.text.toString()
        )
        CoroutineScope(Dispatchers.IO).launch {
            detailsDao.insert(details)

        }
    }
//CORRECT
//    fun updateOne(position: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            detailsDao.updateDetail(position)
//        }
//    }

    fun updateOne(details: Details)
    {
        CoroutineScope(Dispatchers.IO).launch {
            detailsDao.update(details)
        }
    }



//    fun updateDetails(personName: EditText,personID: EditText,personMailID: EditText){
//        val details = Details(0,personName.text.toString(),personID.text.toString(),personMailID.text.toString())
//        GlobalScope.launch {
//            detailsDao.update(details)
//        }
//    }
}





