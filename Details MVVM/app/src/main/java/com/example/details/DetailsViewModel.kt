package com.example.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.details.database.Details
import com.example.details.database.DetailsDao
import com.example.details.database.DetailsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application)
{
    private var job: Job? = null
    val database: DetailsDatabase? = DetailsDatabase.getInstance(application)
    val detailsDao: DetailsDao = database!!.detailsDao()

    fun getData(): LiveData<List<Details>> {
        return detailsDao.getAllDetails()
    }

    fun deleteOne(position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            detailsDao.deleteDetail(position)
        }

    }
//    fun updateOne(position: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            detailsDao.updateDetail(position)
//        }
//    }

}

