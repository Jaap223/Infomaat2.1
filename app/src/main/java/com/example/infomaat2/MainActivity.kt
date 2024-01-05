// MainActivity.kt
package com.example.infomaat2

import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler
    private lateinit var dbHelper: MyDBHelper
    private lateinit var homeAdapter: HomeAdapter // You may need to adjust this based on your adapter type for ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        dbHelper = MyDBHelper(this)

        // Get posts list from the database
        val postsList: List<Post> = dbHelper.getPostsList()

        // Set up ListView and HomeAdapter (or your custom adapter for ListView)
        homeAdapter = HomeAdapter(postsList)
        val listView: ListView = findViewById(R.id.listViewPosts)
        listView.adapter = homeAdapter
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}

