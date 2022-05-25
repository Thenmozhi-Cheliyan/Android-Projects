package com.example.trial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  number1= findViewById(R.id.num1) as EditText
        val  number2= findViewById(R.id.num2) as EditText
        val result= findViewById(R.id.result) as EditText
        val btn1= findViewById(R.id.button) as Button

        btn1.setOnClickListener{
            val val1=number1.text.toString().toInt()
            val val2=number2.text.toString().toInt()
            val val3=val1+val2
            result.setText(val3.toString())
        }
    }
}



