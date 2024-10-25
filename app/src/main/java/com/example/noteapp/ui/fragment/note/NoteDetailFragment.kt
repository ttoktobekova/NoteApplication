package com.example.noteapp.ui.fragment.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.ui.utils.PreferenceHelper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteDetailFragment : Fragment() {
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private val preferenceHelper = PreferenceHelper()
    private var noteID: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceHelper.unit(requireContext())
        updateNote()
        setupListeners()
        setupColorListeners()
        addTextWatcher()
        updateNoteBackground(preferenceHelper.getSelectedColor())

    }

    private fun updateNote() {
        arguments?.let { args ->
            noteID = args.getInt("noteID", -1)
        }
        if (noteID != -1) {
            val argsNote = App.appDataBase?.noteDao()?.getNoteById(noteID)
            binding.btnAddText.visibility = View.VISIBLE
            argsNote?.let { item ->
                binding.titleEt.setText(item.title)
                binding.descriptionEt.setText(item.description)
            }
        }

    }

    private fun setupListeners() = with(binding) {
        binding.btnAddText.setOnClickListener {
            val etTitle = titleEt.text.toString().trim()
            val etDescription = descriptionEt.text.toString().trim()
            val color = preferenceHelper.getSelectedColor()
            val currentTime = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
                .format(Calendar.getInstance().time)

            if (noteID != -1) {
                val updateNote = NoteModel(etTitle, etDescription, color, currentTime)
                updateNote.id = noteID
                App.appDataBase?.noteDao()?.updateNote(updateNote)
            } else {
                App.appDataBase?.noteDao()
                    ?.insert(NoteModel(etTitle, etDescription, color, currentTime))

            }

            findNavController().navigateUp()
        }
    }


    private fun setupColorListeners() = with(binding) {
        btnGray.setOnClickListener { saveColor(R.color.btn_gray) }
        btnWhite.setOnClickListener { saveColor(R.color.btn_white) }
        btnRed.setOnClickListener { saveColor(R.color.btn_red) }
    }

    private fun saveColor(colorResId: Int) {
        val color = resources.getColor(colorResId, null)
        preferenceHelper.saveSelectedColor(color)
        updateNoteBackground(color)
    }

    private fun updateNoteBackground(color: Int) {
        binding.root.setBackgroundColor(color)
    }


    private fun addTextWatcher() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toggleButtonVisibility()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        binding.titleEt.addTextChangedListener(textWatcher)
        binding.descriptionEt.addTextChangedListener(textWatcher)

    }

    private fun toggleButtonVisibility() {
        val isTitleNotEmpty = binding.titleEt.text.toString().trim().isNotEmpty()
        val isDescriptionNotEmpty = binding.descriptionEt.text.toString().trim().isNotEmpty()

        binding.btnAddText.visibility = if (isTitleNotEmpty && isDescriptionNotEmpty) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

}