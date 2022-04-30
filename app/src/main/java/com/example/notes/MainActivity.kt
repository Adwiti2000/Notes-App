package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding


private lateinit var mainBinding: ActivityMainBinding
private lateinit var viewModel: NoteViewModel
class MainActivity : AppCompatActivity(), INotesRVAdapter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter=NotesRVA(this,this)
        mainBinding.recyclerView.adapter = adapter

        viewModel=ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe( this, Observer {list ->
            list?.let {
            adapter.updateList(it)
            }

        })

        mainBinding.addButton.setOnClickListener {
            submitData()
        }

    }

    override fun onItemCLicked(note: Notes) {
       viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted Successfully", Toast.LENGTH_LONG).show()
    }

    private fun submitData() {
        val noteText= mainBinding.input.text.toString()
        if (noteText.isNotEmpty()){
            val note = Notes(0, noteText)
            viewModel.insertNote(note)
            Toast.makeText(this, "$noteText Inserted Successfully", Toast.LENGTH_LONG).show()
        }
    }
}