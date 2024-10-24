package com.example.noteapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.ui.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var pref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        setContentView(binding.root)
        isView()
    }

    private fun isView() {
        pref = PreferenceHelper()
        pref.unit(this)
        if (!pref.isShow()) {
            navController.navigate(R.id.onBoardFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}