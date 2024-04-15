package com.example.githubuser.Main.AppActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubuser.BackupAPI.InfoUser
import com.example.githubuser.databinding.UserItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter : RecyclerView.Adapter<Adapter.ListViewHolder>() {
    private var callItem: OnItemClickCallback? = null
    private val ListofUsers = ArrayList<InfoUser>()

    fun setOnItemClickCallback (CallItem: OnItemClickCallback){
        this.callItem = CallItem
    }

    inner class ListViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: InfoUser) {
            binding.root.setOnClickListener {
                callItem?.onItemClicked(user)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(ivUser)
                tvUsername.text = user.login }
        }
    }
    override fun getItemCount(): Int = ListofUsers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(ListofUsers[position]) }
    interface OnItemClickCallback{
        fun onItemClicked(data: InfoUser) }
    fun setArrayList(users: ArrayList<InfoUser>){
        ListofUsers.clear()
        ListofUsers.addAll(users)
        notifyDataSetChanged() }
}