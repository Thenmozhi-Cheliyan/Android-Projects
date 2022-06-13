package com.example.details.database

import android.os.Parcelable
import android.widget.EditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName="details_table")
@Parcelize
data class Details(
    @PrimaryKey(autoGenerate = true)
    var detailsId:Int,
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="employeeID")
    var employeeID: String,
    @ColumnInfo(name="mailID")
    var mailID: String
): Parcelable