package com.example.memorynotesapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memorynotesapp.R
import com.example.memorynotesapp.framework.NotesViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val notesAdapter = NotesAdapter(arrayListOf())
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }

        floatingActionButtonCreate.setOnClickListener { goToNoteFragment() }

        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        observeViewModel()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observeViewModel() {
        viewModel.notes.observe(this) { notes ->
            progressBar.visibility = View.GONE
            recyclerViewNotes.visibility = View.VISIBLE
            notesAdapter.refresh(notes.sortedByDescending { note -> note.updateAt })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun goToNoteFragment(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment()
        Navigation.findNavController(recyclerViewNotes).navigate(action)
    }

}