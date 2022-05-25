package com.example.handgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.start_button)
        val image1 = findViewById<ImageView>(R.id.imageView1)
        val image2 = findViewById<ImageView>(R.id.imageView2)

        button.setOnClickListener{
            val n1 = (1..3).random()
            val n2 = (1..3).random()

            image1.setImageResource(when(n1)
            {
                1->R.drawable.paper
                2->R.drawable.scissor
                3->R.drawable.stone
                else->throw Exception("n must be between 1 to 3")
            })

            image2.setImageResource(when(n2)
            {
                1->R.drawable.paper
                2->R.drawable.scissor
                3->R.drawable.stone
                else->throw Exception("n must be between 1 to 3")
            })

            if(n1>n2)
            {
                Toast.makeText(this,"PLAYER-1 WINS....",Toast.LENGTH_SHORT).show()
            }
            else if(n1<n2)
            {
                Toast.makeText(this,"PLAYER-2 WINS....",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"DRAW.....",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
