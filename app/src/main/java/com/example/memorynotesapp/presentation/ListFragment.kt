package com.example.memorynotesapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.memorynotesapp.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButtonCreate.setOnClickListener { goToNoteFragment() }
    }

    private fun goToNoteFragment(id: Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(recyclerViewNotes).navigate(action)
    }

}