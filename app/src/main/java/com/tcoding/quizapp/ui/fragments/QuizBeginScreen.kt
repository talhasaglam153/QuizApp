package com.tcoding.quizapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tcoding.quizapp.R
import com.tcoding.quizapp.databinding.FragmentQuizBeginScreenBinding
import com.tcoding.quizapp.model.Category
import com.tcoding.quizapp.obj.BLL
import com.tcoding.quizapp.ui.adapter.CategoryAdapter


class QuizBeginScreen : Fragment() {

    lateinit var binding: FragmentQuizBeginScreenBinding
    lateinit var categoryList: ArrayList<Category>
    var limit = 0
    var categories = ""
    lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuizBeginScreenBinding.inflate(inflater, container, false)
        categoryList = ArrayList<Category>()
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategory.setHasFixedSize(true)


        var startPoint = 0
        var endPoint = 0
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvQuestionCount.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                if(p0 != null) {
                    startPoint  = p0.progress
                }
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                if(p0 != null) {
                    endPoint  = p0.progress
                }
            }

        })

        initRecyclerView()

        return binding.root
    }

    fun getCategoriesList(): String{
        val selectedList = BLL.selectList()
        for(category in selectedList) {
            categories += category.uri+","
        }
        return categories.dropLast(1)
    }


    override fun onResume() {
        super.onResume()


        binding.button.setOnClickListener {
            //findNavController().navigate(R.id.action_quizBeginScreen_to_questionFragment)
            limit = binding.tvQuestionCount.text.toString().toInt()
            categories = getCategoriesList()
            val action = QuizBeginScreenDirections.actionQuizBeginScreenToQuestionFragment(limit, categories)

            findNavController().navigate(action)

        }

        binding.floatingActionButton.setOnClickListener {
          binding.rvCategory.layoutManager?.scrollToPosition(categoryList.size-1)
        }
    }

    fun initRecyclerView() {

        if(categoryList.size == 0) {
            BLL.createCategory()
            categoryList = BLL.getAllCategory()
        }
        categoryAdapter = CategoryAdapter(categoryList, ::itemClick)
        binding.rvCategory.adapter = categoryAdapter

    }


    fun itemClick(position: Int) {

        for(i in 0..(categoryList.size-1)) {
            if (i == position) {
                categoryList[position].isSelected = true
            }else {
                categoryList[i].isSelected = false
            }

        }

    }

}