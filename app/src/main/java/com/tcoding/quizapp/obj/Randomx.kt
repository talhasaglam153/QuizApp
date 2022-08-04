package com.tcoding.quizapp.obj

object Randomx {


    fun correctAnswer(): Int
    {
        val random = (1..4).random()
        return random
    }
}