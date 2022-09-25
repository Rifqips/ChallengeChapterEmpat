package com.rifqipadisiliwangi.noteappnavigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqipadisiliwangi.noteappnavigation.NoteAdapter
import com.rifqipadisiliwangi.noteappnavigation.room.DataNote
import com.rifqipadisiliwangi.noteappnavigation.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<DataNote>>

    init {
        allNote = MutableLiveData()
        getAllUser()

    }

    fun getAllNoteObservers(): MutableLiveData<List<DataNote>>{
        return allNote
    }

    fun getAllUser(){
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }

    }

    fun insertNote(entity: DataNote){
        val noteDao = NoteDatabase.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getAllUser()
    }

    fun deleteNote(entity: DataNote){
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            userDao.deleteNote(entity)
            getAllUser()
        }
    }

    fun updateNote(entity: DataNote){
        val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
        userDao.updateNote(entity)
        getAllUser()
    }

}