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
    lateinit var dataIsLoad : MutableLiveData<Boolean>

    init {
        questionsLiveData = MutableLiveData<ArrayList<QuizData>>()
        dataIsLoad = MutableLiveData<Boolean>()
        dataIsLoad.value = false
    }

    fun getLiveIsLoadData(): MutableLiveData<Boolean> {
        return dataIsLoad
    }

    fun getLiveData(): MutableLiveData<ArrayList<QuizData>> {
        return questionsLiveData
    }

    fun callAPI(categories: String, limit: Int) {

        GlobalScope.launch(Dispatchers.IO) {

            var api = RetroInstance.getRetroInstance().create(RetroService::class.java)

            var response = api.getQuestion(categories, limit).awaitResponse()

            if(response.isSuccessful) {

                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    questionsLiveData.postValue(data)
                    dataIsLoad.value = true
                }

            }else {
                dataIsLoad.value = false
            }


        }



    }




}