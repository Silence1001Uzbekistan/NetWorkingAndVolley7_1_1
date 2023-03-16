package com.example.homework7_1_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.homework7_1_1.Models.UserItem
import com.example.homework7_1_1.databinding.ActivityMainBinding
import com.example.networkingpractise.Adapter.UserAdapter
import com.example.networkingpractise.utils.NetWorkHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: UserAdapter
    lateinit var requestQueue: RequestQueue

    lateinit var netWorkHelper: NetWorkHelper

    var url = "https://api.github.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        netWorkHelper = NetWorkHelper(this)



    }

    override fun onResume() {
        super.onResume()

        if (netWorkHelper.isNetWorkConnected()) {

            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

            val jsonArrayRequest =
                JsonArrayRequest(Request.Method.GET, url, null, object : Response.Listener<JSONArray> {
                    override fun onResponse(response: JSONArray?) {


                        val type = object : TypeToken<List<UserItem>>() {}.type
                        var list: List<UserItem> = Gson().fromJson(response.toString(), type)

                        userAdapter = UserAdapter(list)

                        binding.rv.adapter = userAdapter


                    }

                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {


                    }

                })

            requestQueue.add(jsonArrayRequest)



        } else {

            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()

        }

    }
}