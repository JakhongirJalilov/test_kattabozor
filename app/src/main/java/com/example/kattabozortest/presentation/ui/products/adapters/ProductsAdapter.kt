package com.example.kattabozortest.presentation.ui.products.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kattabozortest.data.remote.response.Offer
import com.example.kattabozortest.databinding.ItemProductBinding

class ProductsAdapter : ListAdapter<Offer, ProductsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position)!!)

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Offer) {
            binding.apply {
                Glide
                    .with(binding.root.context)
                    .load(data.image.url)
                    .override(data.image.width.toInt(), data.image.height.toInt())
                    .centerCrop()
                    .into(img)
                nameProduct.text = data.name
                brand.text = data.brand
                merchant.text = data.merchant
            }
        }
    }
}

class NewsDiffCallback: DiffUtil.ItemCallback<Offer>(){
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean = areItemsTheSame(oldItem, newItem)
}