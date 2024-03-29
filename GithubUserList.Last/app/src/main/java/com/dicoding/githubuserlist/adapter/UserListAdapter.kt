package com.dicoding.githubuserlist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.databinding.GithubUserBinding
import com.dicoding.githubuserlist.detailuser.DetailUserActivity

class UserListAdapter : ListAdapter<UserItems, UserListAdapter.UserViewHolder>(DIFF_CALLBACK) {
    class UserViewHolder(val binding: GithubUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(githubList: UserItems) {
            binding.nameUser.text = githubList.login
            Glide
                .with(itemView.context)
                .load(githubList.avatarUrl)
                .fitCenter()
                .into(binding.imgUser)

            binding.root.setOnClickListener {
                val intentToDetail = Intent(binding.root.context, DetailUserActivity::class.java)
                intentToDetail.putExtra(DetailUserActivity.EXTRA_ID, githubList)
                binding.root.context.startActivity(intentToDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = GithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val githubUser = getItem(position)
        holder.bind(githubUser)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItems>() {
            override fun areContentsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: UserItems, newItem: UserItems): Boolean {
                return oldItem == newItem
            }
        }
    }
}

