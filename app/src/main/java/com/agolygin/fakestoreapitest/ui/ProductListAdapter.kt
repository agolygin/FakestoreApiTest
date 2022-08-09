package com.agolygin.fakestoreapitest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agolygin.fakestoreapitest.R
import com.agolygin.fakestoreapitest.data.ProductData

class ProductDataViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.textTitle)
    val price: TextView = view.findViewById(R.id.textPrice)
}

class ProductListAdapter(private val items: ArrayList<ProductData>?,
                         private val listener: (ProductData) -> Unit) : RecyclerView.Adapter<ProductDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDataViewHolder {
        return ProductDataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false))
    }

    override fun onBindViewHolder(holder: ProductDataViewHolder, position: Int) {
        items?.let {
            val item = it[position]
            holder.title.text = item.title
            holder.price.text = holder.itemView.context?.getString(R.string.text_price, item.price)
            holder.itemView.setOnClickListener { listener(item) }
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}