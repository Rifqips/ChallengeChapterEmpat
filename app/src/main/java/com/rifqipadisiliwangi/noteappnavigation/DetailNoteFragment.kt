package com.rifqipadisiliwangi.noteappnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.noteappnavigation.databinding.FragmentDetailNoteBinding
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote

@Suppress("CAST_NEVER_SUCCEEDS")
class DetailNoteFragment : Fragment() {

    lateinit var binding: FragmentDetailNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailNoteFragment_to_homeFragment)
        }

        val getDetail = arguments?.getSerializable( "detail") as DataNote
//        Toast.makeText(context,"${getDetail}", Toast.LENGTH_SHORT).show()

        binding.detailTitle.text = getDetail.title
        binding.detailNote.text = getDetail.content
    }
}