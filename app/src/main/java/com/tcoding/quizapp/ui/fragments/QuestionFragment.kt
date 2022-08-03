package com.tcoding.quizapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tcoding.quizapp.databinding.FragmentQuestionBinding
import com.tcoding.quizapp.model.Quiz
import com.tcoding.quizapp.model.QuizData
import com.tcoding.quizapp.viewmodel.QuestionViewModel


class QuestionFragment : Fragment() {

    lateinit var binding: FragmentQuestionBinding
    lateinit var question: ArrayList<QuizData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false)

        question = ArrayList<QuizData>()
        initViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        var i = 0

    }

    fun initViewModel() {
        var viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        viewModel.getLiveData().observe(requireActivity(), Observer {
            question = it

        })

        viewModel.callAPI()

    }


}