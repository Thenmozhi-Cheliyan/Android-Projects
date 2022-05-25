package com.example.colormyviews

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var text1: View
    private lateinit var text2: View
    private lateinit var text3: View
    private lateinit var text4: View
    private lateinit var text5: View
    private lateinit var layout: View
    private lateinit var redButton: View
    private lateinit var yellowButton: View
    private lateinit var greenButton: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        return super.onCreateView(parent, name, context, attrs)


    }
//    private val text1= findViewById<View>(R.id.box_one_text)
//    private val text2 = findViewById<View>(R.id.box_two_text)
//    private val text3 = findViewById<View>(R.id.box_three_text)
//    private val text4 = findViewById<View>(R.id.box_four_text)
//    private val text5 = findViewById<View>(R.id.box_five_text)
//    private val layout =findViewById<View>(R.id.constraint_layout)
//    private val redButton = findViewById<View>(R.id.button_red)
//    private val greenButton = findViewById<View>(R.id.button_green)
//    private val yellowButton = findViewById<View>(R.id.button_yellow)

    private fun setListeners() {
        text1= findViewById(R.id.box_one_text)
        text2 = findViewById(R.id.box_two_text)
        text3 = findViewById(R.id.box_three_text)
        text4 = findViewById(R.id.box_four_text)
        text5 = findViewById(R.id.box_five_text)
        layout =findViewById(R.id.constraint_layout)
        redButton = findViewById(R.id.button_red)
        greenButton = findViewById(R.id.button_green)
        yellowButton = findViewById(R.id.button_yellow)

        val clickableViews: List<View> =
            listOf(text1,text2,text3,text4,text5,layout,redButton,greenButton,yellowButton)
        for(item in clickableViews)
        {
            item.setOnClickListener{makeColored(it)}
        }
    }
    private fun makeColored(view: View) {
//        val text4 = findViewById<View>(R.id.box_four_text)
//        val text5 = findViewById<View>(R.id.box_five_text)
        when (view.id) {


            // Boxes using Color class colors for background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.box_three_text -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_text -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_text -> view.setBackgroundResource(android.R.color.holo_green_light)



            R.id.button_red -> text3.setBackgroundResource(R.color.my_red)
            R.id.button_yellow -> text4.setBackgroundResource(R.color.my_yellow)
            R.id.button_green ->text5.setBackgroundResource(R.color.my_green)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}