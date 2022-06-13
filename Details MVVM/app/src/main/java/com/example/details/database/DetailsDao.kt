package com.example.details.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.details.AddDetailsViewModel

@Dao

interface DetailsDao {
    @Insert
    fun insert(details: Details)

    @Update
    fun update(details: Details)

    @Query("DELETE FROM details_table")
    fun clear()

    @Query("SELECT * FROM details_table")
    fun getAllDetails(): LiveData<List<Details>>

    @Query("SELECT * FROM details_table where detailsId=:detailsId")
    fun selectDetails(detailsId: Int): Details

    @Query("DELETE  FROM details_table where detailsId=:detailsId")
    fun deleteDetail(detailsId : Int)

    @Query("UPDATE details_table SET mailID = mailID Where detailsId=:detailsId")
    fun updateDetail(detailsId: Int)
}