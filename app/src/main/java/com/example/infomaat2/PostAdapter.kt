package com.example.infomaat2

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val cursor: Cursor) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(itemView)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        cursor?.moveToPosition(position)
        holder.titleTextView.text = cursor?.getString(cursor.getColumnIndex("title"))
        holder.contentTextView.text = cursor?.getString(cursor.getColumnIndex("content"))
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }
}
