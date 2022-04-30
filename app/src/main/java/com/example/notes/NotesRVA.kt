package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class NotesRVA(private val context: Context,private  val listener:INotesRVAdapter) : RecyclerView.Adapter<NotesRVA.NoteViewHolder>() {

    val allNotes = ArrayList<Notes>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder =
            NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemCLicked(allNotes[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote=allNotes[position]
       holder.textView.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

fun updateList(newList: List<Notes>){
    allNotes.clear()
allNotes.addAll(newList)
    notifyDataSetChanged()
}
}
interface INotesRVAdapter
{
    fun onItemCLicked(note:Notes)
}
