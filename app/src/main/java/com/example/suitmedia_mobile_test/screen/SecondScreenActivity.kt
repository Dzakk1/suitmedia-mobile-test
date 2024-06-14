package com.example.suitmedia_mobile_test.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia_mobile_test.R
import com.example.suitmedia_mobile_test.adapter.UserAdapter
import com.example.suitmedia_mobile_test.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewSetup()
        actionSetup()
    }

    private fun viewSetup() {

        val welcomeName = intent.getStringExtra(EXTRA_NAME).toString()
        Log.d(TAG, "getWelcomeName : $welcomeName")

        val userName = intent.getStringExtra(EXTRA_USERNAME)
        Log.d(TAG, "getUserName : $userName")

        binding.welcomeUser.text = welcomeName

        if (userName.isNullOrEmpty()) {
            binding.selectedUser.text = getString(R.string.selected_username)
        } else {
            binding.selectedUser.text = userName
        }
    }

    private fun actionSetup() {
        binding.btnChooseUser.setOnClickListener {

            val welcomeName = intent.getStringExtra(EXTRA_NAME).toString()

            val intent = Intent(this, ThridScreenActivity::class.java)
            intent.putExtra(UserAdapter.WELCOME_NAME, welcomeName)
            Log.d(TAG, "sendNameToAdapter : $welcomeName")

            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "SecondScreenActivity"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERNAME = "extra_username"
    }
}


