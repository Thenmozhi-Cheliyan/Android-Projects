package com.example.trialnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.trialnavigation.databinding.FragmentFirstBinding
import com.example.trialnavigation.databinding.FragmentSeconddBinding


class SeconddFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_secondd, container, false)
        val binding : FragmentSeconddBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_secondd, container, false)
        binding.buttonTwo.setOnClickListener{
                view : View -> Navigation.findNavController(view).navigate(R.id.action_seconddFragment_to_thirdFragment)
        }
        return binding.root
    }


}