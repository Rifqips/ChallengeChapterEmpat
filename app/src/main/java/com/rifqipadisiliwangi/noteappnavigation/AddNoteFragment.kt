package com.rifqipadisiliwangi.noteappnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentAddNoteBinding
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote
import com.rifqipadisiliwangi.noteappnavigation.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class AddNoteFragment : Fragment() {

    lateinit var binding : FragmentAddNoteBinding
    var dbNote : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = NoteDatabase.getInstance(requireActivity())

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }

        binding.btnSaveNote.setOnClickListener {
            addNote()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
            Toast.makeText(context,"Berhasil ditambahkan.", Toast.LENGTH_LONG).show()

        }
    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.noteTitle.text.toString()
            var note = binding.noteContent.text.toString()

            dbNote!!.noteDao().insertNote(DataNote(0,title, note))
        }
    }
}