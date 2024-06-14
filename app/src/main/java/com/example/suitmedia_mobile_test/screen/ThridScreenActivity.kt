package com.example.suitmedia_mobile_test.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia_mobile_test.adapter.UserAdapter
import com.example.suitmedia_mobile_test.data.response.DataItem
import com.example.suitmedia_mobile_test.databinding.ActivityThridScreenBinding
import com.example.suitmedia_mobile_test.viewmodel.ThirdScreenViewModel

class ThridScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThridScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThridScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val welcomeName = intent.getStringExtra(UserAdapter.WELCOME_NAME).toString()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        val thirdScreenViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ThirdScreenViewModel::class.java)
        thirdScreenViewModel.user.observe(this) { users ->
            setUserData(users, welcomeName)
        }
    }


    private fun setUserData(users : List<DataItem>, welcomeName: String) {
        val adapter = UserAdapter(welcomeName)
        adapter.submitList(users)
        binding.rvUser.adapter = adapter
    }
}