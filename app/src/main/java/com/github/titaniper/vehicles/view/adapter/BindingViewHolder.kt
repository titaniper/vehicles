package com.github.titaniper.vehicles.view.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder<out V : ViewDataBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root)