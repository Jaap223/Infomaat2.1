package com.example.infomaat2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity2 : AppCompatActivity() {

    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        getOpleidingData();

    }


    fun getOpleidingData(){

        val listView = findViewById<ListView>(R.id.lv_json)
        val items = ArrayList<String>()
        var requestQueue = Volley.newRequestQueue(this.applicationContext)
        val request = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            var jArray = JSONArray(data)
            for ( i in 0 until jArray.length()){
                val json_data = jArray.getJSONObject(i)
                val opleiding : String = json_data.getString("opleiding")
                items.add(opleiding)
            }

            Thread.sleep(1000)
            listView.adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items)


            listView.setOnItemClickListener { parent, view, position,id ->
                Toast.makeText(applicationContext,
                    parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show()
            }

        },  Response.ErrorListener { })

        requestQueue.add((request))

    }
}