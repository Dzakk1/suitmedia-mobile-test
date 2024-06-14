package com.example.suitmedia_mobile_test.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia_mobile_test.data.response.DataItem
import com.example.suitmedia_mobile_test.databinding.ItemUserBinding
import com.example.suitmedia_mobile_test.screen.SecondScreenActivity

class UserAdapter(private val welcomeName: String) : ListAdapter<DataItem, UserAdapter.MyViewHolder> (DIFF_CALLBACK) {

    class MyViewHolder (val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem, welcomeName: String) {
            binding.userName.text = user.firstName + " " + user.lastName
            binding.userEmail.text = user.email

            Glide.with(itemView)
                .load(user.avatar)
                .into(binding.imgUser)

            val clickHandler = binding.root.context

            itemView.setOnClickListener {
                val intent = Intent(clickHandler, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_USERNAME, user.firstName + " " + user.lastName)
                Log.d(TAG, "sendUsername : $user")


                intent.putExtra(SecondScreenActivity.EXTRA_NAME, welcomeName)
                Log.d(TAG, "sendWelcomeName : $welcomeName")

                clickHandler.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, welcomeName)
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {

            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }

        private const val TAG = "UserAdapter"
        const val WELCOME_NAME = "welcome_name"
    }

}