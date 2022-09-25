package com.rifqipadisiliwangi.noteappnavigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rifqipadisiliwangi.noteappnavigation.databinding.ItemNoteBinding
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote
import com.rifqipadisiliwangi.noteappnavigation.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class NoteAdapter(var listNote : List<DataNote>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var DBNote: NoteDatabase? = null

    class ViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.noteTitle.text = listNote[position].title
        holder.binding.noteContent.text = listNote[position].content
        holder.binding.btnDeleteNote.setOnClickListener{
            DBNote = NoteDatabase.getInstance(it.context)
            run {
                GlobalScope.async {
                    val del = DBNote?.noteDao()?.deleteNote(listNote[position])
                    (holder.itemView.context as HomeFragment).run{
                        (holder.itemView.context as HomeFragment).getAllNote()
                    }
                }
            }
            Toast.makeText(it.context,"Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            }

        holder.binding.rlContent.setOnClickListener(object :  View.OnClickListener{
            override fun onClick(p0: View?) {
                val bun = Bundle()
                var data = DataNote(listNote[position].id, listNote[position].title, listNote[position].content)
                bun.putSerializable("detail", data as DataNote)
                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailNoteFragment,bun)

            }
        })
//
        holder.binding.tvUpdate.setOnClickListener(object :  View.OnClickListener{
            override fun onClick(p0: View?) {
                val bun = Bundle()
                var data = DataNote(listNote[position].id, listNote[position].title, listNote[position].content)
                bun.putSerializable("dataedit", data as DataNote)
                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_editFragment,bun)

            }
        })
}

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setNoteData(listNote: ArrayList<DataNote>) {
        this.listNote = listNote
    }
}