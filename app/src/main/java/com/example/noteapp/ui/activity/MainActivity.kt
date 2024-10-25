package com.example.noteapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
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
        setupAppBarVisibility()
    }

    private fun isView() {
        pref = PreferenceHelper()
        pref.unit(this)

        if (!pref.isShow()) {
            navController.navigate(R.id.onBoardFragment)
        } else if (!pref.isShowSingUp()) {
            navController.navigate(R.id.singUpFragment)
        }
    }


    private fun setupAppBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val layoutParams =
                binding.fragmentContainer.layoutParams as ConstraintLayout.LayoutParams

            if (destination.id == R.id.noteFragment) {
                binding.appBar.visibility = View.VISIBLE
                layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.appbar_height)
            } else {
                binding.appBar.visibility = View.GONE
                layoutParams.topMargin = 0
            }
            binding.fragmentContainer.layoutParams = layoutParams //
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}