package com.tcoding.quizapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tcoding.quizapp.R
import com.tcoding.quizapp.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    lateinit var binding: FragmentScoreBinding
    val args: ScoreFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false)

        binding.tvResult.setText(args.score.toString())

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_scoreFragment_to_quizBeginScreen)
        }

        binding.btnQuit.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }
}