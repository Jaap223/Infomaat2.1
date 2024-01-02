package com.example.infomaat2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private var posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        val postContent: TextView = itemView.findViewById(R.id.postContent)
        val editButton: ImageButton = itemView.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        holder.postTitle.text = post.title
        holder.postContent.text = post.content


        holder.editButton.setOnClickListener {
            val intent = Intent(it.context, EditPostActivity::class.java)
            intent.putExtra("postId", post.id)
            intent.putExtra("postTitle", post.title)
            intent.putExtra("postContent", post.content)
            it.context.startActivity(intent)
        }

       
        holder.deleteButton.setOnClickListener {

            val updatedPosts = posts.toMutableList()
            updatedPosts.removeAt(position)


            val dbHelper = MyDBHelper(it.context)
            dbHelper.deletePost(post.id)


            posts = updatedPosts
            notifyDataSetChanged()
        }
    }


    override fun getItemCount() = posts.size
}


