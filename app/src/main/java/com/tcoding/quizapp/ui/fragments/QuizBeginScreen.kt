package com.tcoding.quizapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tcoding.quizapp.R
import com.tcoding.quizapp.databinding.FragmentQuizBeginScreenBinding


class QuizBeginScreen : Fragment() {

    lateinit var binding: FragmentQuizBeginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuizBeginScreenBinding.inflate(inflater, container, false)

        binding.btnStart.setOnClickListener {

            findNavController().navigate(R.id.action_quizBeginScreen_to_questionFragment)

        }

        return binding.root
    }

}