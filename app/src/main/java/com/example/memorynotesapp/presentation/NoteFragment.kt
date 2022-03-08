package com.example.memorynotesapp.presentation

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.memorynotesapp.R
import com.example.memorynotesapp.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        floatingActionButtonCheck.setOnClickListener {
            if (editTextTitle.text.toString() != "" || editTextContent.text.toString() != "" ) {
                val time = System.currentTimeMillis()
                currentNote.title = editTextTitle.text.toString()
                currentNote.content = editTextContent.text.toString()
                currentNote.updateAt = time
                if (currentNote.id == 0L) {
                    currentNote.createAt = time
                }
                viewModel.save(currentNote)
            }
            Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeViewModel() {
        viewModel.isSaved.observe(this, Observer {
          if (it) {
              Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
              hideKeyboard()
              Navigation.findNavController(editTextTitle).popBackStack()
          } else {
              Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
          }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextTitle.windowToken, 0)
    }

}