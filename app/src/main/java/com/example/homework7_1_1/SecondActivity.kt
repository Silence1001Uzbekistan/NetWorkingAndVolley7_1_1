package com.example.homework7_1_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.homework7_1_1.Models.UserItem
import com.example.homework7_1_1.ModelsTwo.CurrencyItem
import com.example.homework7_1_1.databinding.ActivitySecondBinding
import com.example.homework7_1_1.databinding.BottomDialogBinding
import com.example.networkingpractise.Adapter.CurrencyAdapter
import com.example.networkingpractise.Adapter.UserAdapter
import com.example.networkingpractise.utils.NetWorkHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    lateinit var currencyAdapter: CurrencyAdapter
    lateinit var requestQueue: RequestQueue

    lateinit var netWorkHelper: NetWorkHelper

    var url = "http://cbu.uz/uz/arkhiv-kursov-valyut/json/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        netWorkHelper = NetWorkHelper(this)

    }

    override fun onResume() {
        super.onResume()

        if (netWorkHelper.isNetWorkConnected()) {

            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()

            val jsonArrayRequest =
                JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    object : Response.Listener<JSONArray> {
                        override fun onResponse(response: JSONArray?) {


                            val type = object : TypeToken<List<CurrencyItem>>() {}.type
                            var list: List<CurrencyItem> =
                                Gson().fromJson(response.toString(), type)

                            currencyAdapter = CurrencyAdapter(list,
                                object : CurrencyAdapter.OnMyItemClickListener {
                                    override fun itemClick(currencyItem: CurrencyItem) {

                                        val bottomSheetDialog =
                                            BottomSheetDialog(this@SecondActivity)

                                        val bottomDialogBinding =
                                            BottomDialogBinding.inflate(layoutInflater, null, false)

                                        bottomSheetDialog.setContentView(bottomDialogBinding.root)

                                        bottomDialogBinding.dg1.text = currencyItem.Ccy
                                        bottomDialogBinding.dg2.text = currencyItem.CcyNm_UZ
                                        bottomDialogBinding.dg3.text = currencyItem.CcyNm_EN
                                        bottomDialogBinding.dg4.text = currencyItem.CcyNm_RU
                                        bottomDialogBinding.dg5.text = currencyItem.Code
                                        bottomDialogBinding.dg6.text = currencyItem.CcyNm_UZC
                                        bottomDialogBinding.dg7.text = currencyItem.Date
                                        bottomDialogBinding.dg8.text = currencyItem.Diff
                                        bottomDialogBinding.dg9.text = currencyItem.Nominal
                                        bottomDialogBinding.dg10.text = currencyItem.Rate
                                        bottomDialogBinding.dg11.text = currencyItem.id.toString()

                                        bottomSheetDialog.show()

                                    }

                                })

                            binding.rv.adapter = currencyAdapter


                        }

                    },
                    object : Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError?) {


                        }

                    })

            requestQueue.add(jsonArrayRequest)


        } else {

            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()

        }

    }

}