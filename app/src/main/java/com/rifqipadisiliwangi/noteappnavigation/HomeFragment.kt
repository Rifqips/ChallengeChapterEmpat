package com.rifqipadisiliwangi.noteappnavigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentHomeBinding
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote
import com.rifqipadisiliwangi.noteappnavigation.room.NoteDatabase
import com.rifqipadisiliwangi.noteappnavigation.viewmodel.NoteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPrefs : SharedPreferences
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote : NoteAdapter
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContext = requireContext().applicationContext
        sharedPrefs = appContext.getSharedPreferences("USERNAME", Context.MODE_PRIVATE)

        binding.tvUsername.text = sharedPrefs.getString("username","")

        binding.imgProfileUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        NoteDB = NoteDatabase.getInstance(requireActivity())

        noteVm()

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.getAllNoteObservers().observe(requireActivity(),{
            adapterNote.setNoteData(it as ArrayList<DataNote>)
        })

    }

    fun noteVm(){
        adapterNote = NoteAdapter(ArrayList())
        binding.rvKontent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvKontent.adapter = adapterNote
    }

    fun getAllNote(){
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            run{
                adapterNote = NoteAdapter(data!!)
                binding.rvKontent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvKontent.adapter = adapterNote
            }
        }
    }
}