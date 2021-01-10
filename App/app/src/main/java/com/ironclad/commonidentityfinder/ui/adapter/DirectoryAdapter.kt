package com.ironclad.commonidentityfinder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ironclad.api.models.entities.User
import com.ironclad.commonidentityfinder.databinding.ListItemDirectoryBinding

class DirectoryAdapter(val clickListener: (com.ironclad.commonidentityfinder.data.User) -> Unit) :
    ListAdapter<User, DirectoryAdapter.DirectoryViewHolder>(
        object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.toString() == newItem.toString()
            }

        }
    ) {
    inner class DirectoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectoryViewHolder {
        val binding =
            ListItemDirectoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DirectoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DirectoryViewHolder, position: Int) {
        ListItemDirectoryBinding.bind(holder.itemView).apply {
            val user = getItem(position)
            val userTO = com.ironclad.commonidentityfinder.data.User(
                "directory",
                user.age,
                user.gender,
                user.id,
                user.maritalStatus,
                user.name,
                user.placeOfBirth,
                user.imageUrl
            )
            val id = user.id ?: -1

            tvId.text = (id + 1).toString()
            tvName.text = user.name
            tvAge.text = user.age.toString()
            tvGender.text = user.gender
            tvPOB.text = user.placeOfBirth
            tvMS.text = user.maritalStatus
            ivArrow.setOnClickListener {
                clickListener(userTO)
            }
        }
    }
}