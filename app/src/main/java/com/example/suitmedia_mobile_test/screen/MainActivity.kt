package com.example.suitmedia_mobile_test.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia_mobile_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionHandler()
    }


    private fun actionHandler() {
        binding.btnCheck.setOnClickListener {
            val inputPalindrom = binding.palindromeInputText.text.toString().lowercase()
            val palindromeCheck = inputPalindrom.equals(inputPalindrom.reversed())

            if (palindromeCheck) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Yeay!")
                    setMessage("isPalindrome")
                    setPositiveButton("Ok") { _, _ ->
                        binding.palindromeInputText.setText(inputPalindrom)
                    }
                    create()
                    show()
                }

            } else {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Oops!")
                    setMessage("not Palindrome")
                    setPositiveButton("Ok") { _, _ ->
                        binding.palindromeInputText.setText(inputPalindrom)
                    }
                    create()
                    show()
                }
            }
        }

        binding.btnNext.setOnClickListener {
            val name = binding.nameInputText.text.toString()
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)

            Log.d(TAG, "Send Name : $name")

            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}