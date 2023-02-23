package com.example.myownnotesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterNotes(private  var context: Context,private var userListNote: ArrayList<Note>) :RecyclerView.Adapter<AdapterNotes.viewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder1 {
        val viewNote =
            LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item, parent, false)
        return viewHolder1(viewNote)
    }

    override fun onBindViewHolder(holder: viewHolder1, position: Int) {
        var myModel = userListNote.get(position)
        holder.noteTV.text = myModel.noteTitle
        holder.dateTV.setText("Last updated : " + userListNote.get(position).timeStamp)
        holder.itemView.setOnClickListener() {
            val intent = Intent(context, updateActivity::class.java)
            intent.putExtra("title", userListNote.get(position).noteTitle)
            intent.putExtra("notes", userListNote.get(position).noteDescription)

            context.startActivity(intent)
        }
            holder.deleteTV.setOnClickListener() {
                val database = AppDataBase.getDatabase(context)
                val dao = database.getNotesDao()
                dao.deleteUser(userListNote.get(position))
            }
        }

    override fun getItemCount(): Int {
        return userListNote.size
    }

    class viewHolder1(items: View) : RecyclerView.ViewHolder(items) {
        var noteTV: TextView
        var dateTV: TextView
        var deleteTV: TextView

        init {
            noteTV = itemView.findViewById(R.id.idTVNote)
            dateTV = itemView.findViewById(R.id.idTVDate)
            deleteTV = itemView.findViewById(R.id.idIVDelete)
        }
    }
}
