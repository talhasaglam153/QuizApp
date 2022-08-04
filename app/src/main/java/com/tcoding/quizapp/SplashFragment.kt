package com.tcoding.quizapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tcoding.quizapp.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.splashImageView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_quizBeginScreen)
            requireActivity().supportFragmentManager.popBackStack()

        },3000)

        return binding.root
    }


}