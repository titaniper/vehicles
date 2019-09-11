package com.github.titaniper.vehicles.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.*
import androidx.recyclerview.widget.RecyclerView
import com.github.titaniper.vehicles.R
import com.github.titaniper.vehicles.model.VehicleInfo
import com.github.titaniper.vehicles.databinding.MainVehicleItemBinding

class VehicleItemAdapter: RecyclerView.Adapter<BindingViewHolder<MainVehicleItemBinding>>() {
    lateinit var binding: MainVehicleItemBinding
    val map = ObservableArrayMap<Int, Boolean>()
    private var _onChangedFavoriteListener: OnChangedFavoriteListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<MainVehicleItemBinding> {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.main_vehicle_item, parent, false)
        return BindingViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<MainVehicleItemBinding>, position: Int) {
        holder.binding.data = vehicles[position]
        holder.binding.adapter = this
        holder.binding.position = position
        holder.binding.capacityText = String.format("적재용량 : %.1ft", (vehicles[position].capacity * 0.001))
    }

    private val vehicles = ArrayList<VehicleInfo>()

    override fun getItemCount() = vehicles.size

    fun setOnChangedFavoriteListener(listener: OnChangedFavoriteListener) {
        _onChangedFavoriteListener = listener
        map.addOnMapChangedCallback(object : ObservableMap.OnMapChangedCallback<ObservableMap<Int, Boolean>, Int, Boolean>() {
            override fun onMapChanged(sender: ObservableMap<Int, Boolean>, key: Int?) {
                if (_onChangedFavoriteListener != null) {
                    _onChangedFavoriteListener?.onChange(key ?: 0, vehicles[key ?: 0], map.get(key) ?: false)
                }
            }
        })
    }

    fun addVehicles(vehicles: List<VehicleInfo>){
        this.vehicles.addAll(vehicles)
        this.vehicles.forEachIndexed { index, vehicleInfo ->
            map.put(index as Int?, vehicleInfo.favorite)
        }
    }

    interface OnChangedFavoriteListener {
        fun onChange(position: Int, info: VehicleInfo, newState: Boolean)
    }
}