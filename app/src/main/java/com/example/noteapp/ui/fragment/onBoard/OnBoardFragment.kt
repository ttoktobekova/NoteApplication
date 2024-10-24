package com.example.noteapp.ui.fragment.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.OnBoardPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {
    private var _binding: FragmentOnBoardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }


    private fun initialize() {
        binding.viewPager2.adapter = OnBoardPagerAdapter(this)
        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.viewPager2
        val adapter = OnBoardPagerAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)
    }


    private fun setupListener() = with(binding.viewPager2) {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvSkip.visibility = if (position < 2) View.VISIBLE else View.INVISIBLE
                binding.btnStart.visibility = if (position < 2) View.INVISIBLE else View.VISIBLE
                binding.btnStart.setOnClickListener {
                    findNavController().navigate(R.id.noteFragment)
                }

            }
        })
        binding.tvSkip.setOnClickListener {
            setCurrentItem(currentItem + 2, true)
        }
    }
}