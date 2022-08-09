package com.tcoding.quizapp.network

import com.tcoding.quizapp.model.Quiz
import com.tcoding.quizapp.model.QuizData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {


    @GET("/api/questions")
    fun getQuestion(
        @Query("categories") categories:String,
        @Query("limit") limit: Int): Call<ArrayList<QuizData>>





}