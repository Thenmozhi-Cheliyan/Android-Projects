package com.example.trialnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.trialnavigation.databinding.ActivityMainBinding
import com.example.trialnavigation.databinding.ActivityMainTwoBinding

class MainActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_two)

        val actionBar=supportActionBar
        actionBar!!.title="Second Activity"
        val binding = DataBindingUtil.setContentView<ActivityMainTwoBinding>(this,R.layout.activity_main_two)
        binding.activityTwoButton.setOnClickListener{
           val intent = Intent(this,MainActivityThree::class.java)
            startActivity(intent)
        }
    }
}