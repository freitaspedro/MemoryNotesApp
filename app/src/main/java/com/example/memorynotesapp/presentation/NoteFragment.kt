package com.example.memorynotesapp.presentation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.memorynotesapp.R
import com.example.memorynotesapp.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.item_note.*

class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.get(noteId)
        }

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
        viewModel.isSaved.observe(this) {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(editTextTitle).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.currentNote.observe(this) { note ->
            note?.let {
                currentNote = it
                val currentTitle = it.title
                editTextTitle.setText(currentTitle, TextView.BufferType.EDITABLE)
                val currentContent = it.content
                editTextContent.setText(currentContent, TextView.BufferType.EDITABLE)
            }
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editTextTitle.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemDelete -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.delete(currentNote)
                        }
                        .setNegativeButton("Cancel") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

}