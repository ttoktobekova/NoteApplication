package com.example.noteapp.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.utils.PreferenceHelper


class NoteFragment : Fragment() {
private var _binding :FragmentNoteBinding? = null
    private val binding get() = _binding!!
    val sharedPreferences = PreferenceHelper()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners()  = with(binding){
        sharedPreferences.unit(requireContext())
        btnSave.setOnClickListener{
            val et = etText.text.toString().trim()
          //мы снизу дали значение
            sharedPreferences.text = et
            tvSave.text = et
        }
            //мы тут берём сохр значение
        tvSave.text = sharedPreferences.text
    }


}