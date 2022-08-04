package com.tcoding.quizapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {


    companion object {
        val BASE_URL = "https://the-trivia-api.com/"


        fun getRetroInstance(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun correctAnswer(): Int
        {
            val random = (1..4).random()
            return random
        }

    }



}