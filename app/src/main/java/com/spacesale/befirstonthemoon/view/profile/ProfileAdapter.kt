package com.spacesale.befirstonthemoon.view.profile

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spacesale.befirstonthemoon.R

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    //private var profiles = listOf<Profile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_profile, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        //todo
        holder.onBind()
    }

    override fun getItemCount(): Int {
        //todo
        return 1
        //return profiles.size
    }

    //todo
    fun bindProfile(/*profiles: List<Profile>*/)
    {
        //this.profiles = profiles
        notifyDataSetChanged()
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val planetImage = itemView.findViewById<ImageView>(R.id.planet_img)
        private val planetName = itemView.findViewById<TextView>(R.id.planet_name)
        private val sectorsName = itemView.findViewById<TextView>(R.id.sectors)

        //todo добвить биндинг дто в холдер
        fun onBind() {
            planetImage.load(R.drawable.ic_marsdetails)
            planetName.text = "Марс"
            sectorsName.text = "Участок 12364\\nУчасток 24526"
        }

    }

}