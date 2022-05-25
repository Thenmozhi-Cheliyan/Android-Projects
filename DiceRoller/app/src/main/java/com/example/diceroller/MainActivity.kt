package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.rollButton)
        val imageView = findViewById<ImageView>(R.id.imageView)

        button.setOnClickListener{
           Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
            val n = (1..6).random()

        imageView.setImageResource(when (n) {
            1 -> R.drawable.perspective_dice_six_faces_one
            2 -> R.drawable.perspective_dice_six_faces_two
            3 -> R.drawable.perspective_dice_six_faces_three
            4 -> R.drawable.perspective_dice_six_faces_four
            5 -> R.drawable.perspective_dice_six_faces_five
            6 -> R.drawable.dice_six
            else -> throw Exception("n must be between 1 to 6")

        })
        }
        }
    }


