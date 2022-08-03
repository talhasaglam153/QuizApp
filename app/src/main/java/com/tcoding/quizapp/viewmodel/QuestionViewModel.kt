package com.tcoding.quizapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tcoding.quizapp.model.Quiz
import com.tcoding.quizapp.model.QuizData
import com.tcoding.quizapp.network.RetroInstance
import com.tcoding.quizapp.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class QuestionViewModel: ViewModel() {


    lateinit var questionsLiveData: MutableLiveData<ArrayList<QuizData>>

    init {
        questionsLiveData = MutableLiveData<ArrayList<QuizData>>()
    }

    fun getLiveData(): MutableLiveData<ArrayList<QuizData>> {
        return questionsLiveData
    }

    fun callAPI() {

        GlobalScope.launch(Dispatchers.IO) {

            var api = RetroInstance.getRetroInstance().create(RetroService::class.java)

            var response = api.getQuestion(10).awaitResponse()

            if(response.isSuccessful) {
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    questionsLiveData.postValue(data)
                }

            }


        }



    }




}