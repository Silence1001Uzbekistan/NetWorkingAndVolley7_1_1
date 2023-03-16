package com.example.networkingpractise.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework7_1_1.Models.UserItem
import com.example.homework7_1_1.ModelsTwo.CurrencyItem
import com.example.homework7_1_1.databinding.ItemCurrencyBinding
import com.example.homework7_1_1.databinding.ItemUserBinding

class CurrencyAdapter(var list: List<CurrencyItem>,var onMyItemClickListener: OnMyItemClickListener) : RecyclerView.Adapter<CurrencyAdapter.vh>() {

    inner class vh(var itemCurrencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {

        fun onBind(currencyItem: CurrencyItem) {

            itemCurrencyBinding.tv1.text = currencyItem.CcyNm_UZ
            itemCurrencyBinding.tv2.text = currencyItem.CcyNm_EN
            itemCurrencyBinding.tv3.text = currencyItem.CcyNm_RU

            itemCurrencyBinding.root.setOnClickListener {

                onMyItemClickListener.itemClick(currencyItem)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {

        return vh(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: vh, position: Int) {

        holder.onBind(list[position])

    }

    interface OnMyItemClickListener {

        fun itemClick(currencyItem: CurrencyItem)

    }


}