package com.spacesale.befirstonthemoon.view.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Purchase

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    private var purchases = listOf<Purchase>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_profile, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(purchases[position])
    }

    override fun getItemCount(): Int = purchases.size

    fun bindProfile(purchases: List<Purchase>)
    {
        this.purchases = purchases
        notifyDataSetChanged()
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val planetImage = itemView.findViewById<ImageView>(R.id.planet_img)
        private val planetName = itemView.findViewById<TextView>(R.id.planet_name)
        private val sectorsName = itemView.findViewById<TextView>(R.id.sectors)

        fun onBind(purchase: Purchase) {
            planetImage.load(purchase.planetPicture)
            planetName.text = purchase.planetName
            sectorsName.text = purchase.sectorNames
        }

    }

}