package com.rifqipadisiliwangi.noteappnavigation

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentEditBinding
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote
import com.rifqipadisiliwangi.noteappnavigation.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class EditFragment : Fragment() {

    lateinit var binding:FragmentEditBinding
    var dbNote : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }

        var getDataNote = arguments?.getSerializable("dataedit") as DataNote

//        binding.idNote.setText(getDataNote.id)
        binding.editTitle.setText(getDataNote.title)
        binding.editNote.setText(getDataNote.content)

        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote(){
//        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTitle.text.toString()
        var note = binding.editNote.text.toString()

        dbNote = NoteDatabase.getInstance(requireActivity())

        GlobalScope.async {
            run {
                dbNote?.noteDao()?.updateNote(DataNote(0,title,note))
            }
        }
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.editFragment, true).build()
        findNavController().navigate(R.id.action_editFragment_to_homeFragment,null,navOptions)
        Toast.makeText(context,"Berhasil Edit Data",Toast.LENGTH_SHORT).show()
    }
}