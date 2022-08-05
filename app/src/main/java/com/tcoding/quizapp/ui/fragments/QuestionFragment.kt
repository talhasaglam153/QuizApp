package com.tcoding.quizapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tcoding.quizapp.R
import com.tcoding.quizapp.databinding.FragmentQuestionBinding
import com.tcoding.quizapp.model.Quiz
import com.tcoding.quizapp.model.QuizData
import com.tcoding.quizapp.obj.Randomx
import com.tcoding.quizapp.viewmodel.QuestionViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class QuestionFragment : Fragment() {

    var score = 0
    lateinit var binding: FragmentQuestionBinding
    lateinit var question: ArrayList<QuizData>
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        binding.tvScore.setText("Score: $score")
        question = ArrayList<QuizData>()
        initViewModel()
        return binding.root

    }


    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    fun changeQuestion() {

        binding.btnNextQuestion.setOnClickListener {

            if(i==10) {
                i=0
                scoreScreen()
            }else {
                binding.tvAnswer1.isClickable = true
                binding.tvAnswer2.isClickable = true
                binding.tvAnswer3.isClickable = true
                binding.tvAnswer4.isClickable = true

                binding.tvAnswer1.setTextColor(R.color.teal_700)
                binding.tvAnswer2.setTextColor(R.color.teal_700)
                binding.tvAnswer3.setTextColor(R.color.teal_700)
                binding.tvAnswer4.setTextColor(R.color.teal_700)

                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_option)
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_option)


                binding.tvQuestion.setText(question[i].question)

                val random = Randomx.correctAnswer()

                setData(random)
                binding.tvQuestionNumber.text = "${i+1}/${question.size}"
            }



        }


    }

    fun scoreScreen() {
        val direction = QuestionFragmentDirections.actionQuestionFragmentToScoreFragment(score)
        findNavController().navigate(direction)
    }


    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    fun control() {

        tvAnswer1()

        tvAnswer2()

        tvAnswer3()

        tvAnswer4()

    }


    override fun onResume() {
        super.onResume()


        changeQuestion()
        control()



    }

    @SuppressLint("SetTextI18n")
    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)



        viewModel.getLiveData().observe(requireActivity(), Observer {
            question = it

            binding.tvQuestion.text = question[i].question

            val random = Randomx.correctAnswer()

            setData(random)

            binding.tvQuestionNumber.text = "${i+1}/${question.size}"



        })

        viewModel.callAPI()


    }

    fun setData(random: Int) {
        when(random) {
            1-> {
                binding.tvAnswer1.text = question[i].correctAnswer
                binding.tvAnswer2.text = question[i].incorrectAnswers[0]
                binding.tvAnswer3.text = question[i].incorrectAnswers[1]
                binding.tvAnswer4.text = question[i].incorrectAnswers[2]
            }
            2-> {
                binding.tvAnswer2.text = question[i].correctAnswer
                binding.tvAnswer1.text = question[i].incorrectAnswers[0]
                binding.tvAnswer3.text = question[i].incorrectAnswers[1]
                binding.tvAnswer4.text = question[i].incorrectAnswers[2]
            }
            3-> {
                binding.tvAnswer3.text = question[i].correctAnswer
                binding.tvAnswer2.text = question[i].incorrectAnswers[0]
                binding.tvAnswer1.text = question[i].incorrectAnswers[1]
                binding.tvAnswer4.text = question[i].incorrectAnswers[2]
            }
            4-> {
                binding.tvAnswer4.text = question[i].correctAnswer
                binding.tvAnswer2.text = question[i].incorrectAnswers[0]
                binding.tvAnswer3.text = question[i].incorrectAnswers[1]
                binding.tvAnswer1.text = question[i].incorrectAnswers[2]
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    fun tvAnswer1() {

        binding.tvAnswer1.setOnClickListener {


            if(question[i].correctAnswer == binding.tvAnswer1.text) {
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer1.setTextColor(R.color.white)
                score += 10
                binding.tvScore.setText("Score: ${score.toString()}")
            }else if(question[i].correctAnswer == binding.tvAnswer2.text) {
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer2.setTextColor(R.color.white)
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer3.text) {
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer3.setTextColor(R.color.white)
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer4.text) {
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer4.setTextColor(R.color.white)
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.tvAnswer1.isClickable = false
            binding.tvAnswer2.isClickable = false
            binding.tvAnswer3.isClickable = false
            binding.tvAnswer4.isClickable = false
        }

    }

    @SuppressLint("ResourceAsColor")
    fun tvAnswer2() {
        binding.tvAnswer2.setOnClickListener {
            if(question[i].correctAnswer == binding.tvAnswer2.text) {
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer2.setTextColor(R.color.white)
                score += 10
                binding.tvScore.setText("Score: ${score.toString()}")
            }else if(question[i].correctAnswer == binding.tvAnswer1.text) {
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer1.setTextColor(R.color.white)
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer3.text) {
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer3.setTextColor(R.color.white)
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer4.text) {
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer4.setTextColor(R.color.white)
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.tvAnswer1.isClickable = false
            binding.tvAnswer2.isClickable = false
            binding.tvAnswer3.isClickable = false
            binding.tvAnswer4.isClickable = false
        }
    }

    @SuppressLint("ResourceAsColor")
    fun tvAnswer3() {
        binding.tvAnswer3.setOnClickListener {
            if(question[i].correctAnswer == binding.tvAnswer3.text) {
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer3.setTextColor(R.color.white)
                score += 10
                binding.tvScore.setText("Score: ${score.toString()}")
            }else if(question[i].correctAnswer == binding.tvAnswer2.text) {
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer2.setTextColor(R.color.white)
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer1.text) {
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer1.setTextColor(R.color.white)
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer4.text) {
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer4.setTextColor(R.color.white)
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.tvAnswer1.isClickable = false
            binding.tvAnswer2.isClickable = false
            binding.tvAnswer3.isClickable = false
            binding.tvAnswer4.isClickable = false
        }
    }

    @SuppressLint("ResourceAsColor")
    fun tvAnswer4() {
        binding.tvAnswer4.setOnClickListener {
            if(question[i].correctAnswer == binding.tvAnswer4.text) {
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer4.setTextColor(R.color.white)
                score += 10
                binding.tvScore.setText("Score: ${score.toString()}")
            }else if(question[i].correctAnswer == binding.tvAnswer2.text) {
                binding.tvAnswer2.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer2.setTextColor(R.color.white)
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer3.text) {
                binding.tvAnswer3.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer3.setTextColor(R.color.white)
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_false)
            }else if(question[i].correctAnswer == binding.tvAnswer1.text) {
                binding.tvAnswer1.background = requireActivity().getDrawable(R.drawable.question_true)
                binding.tvAnswer1.setTextColor(R.color.white)
                binding.tvAnswer4.background = requireActivity().getDrawable(R.drawable.question_false)
            }
            i++
            binding.tvAnswer1.isClickable = false
            binding.tvAnswer2.isClickable = false
            binding.tvAnswer3.isClickable = false
            binding.tvAnswer4.isClickable = false
        }
    }


}