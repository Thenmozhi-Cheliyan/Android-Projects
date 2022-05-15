package com.example.trialnavigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.trialnavigation.databinding.FragmentSeconddBinding
import com.example.trialnavigation.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_third, container, false)

        val bind  = FragmentThirdBinding.inflate(layoutInflater)
        bind.buttonThree.setOnClickListener{

           val intent = Intent(this@ThirdFragment.requireContext(),MainActivityTwo::class.java)
            startActivity(intent)
        }
        return bind.root
    }
}