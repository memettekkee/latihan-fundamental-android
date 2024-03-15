package com.dicoding.myappbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.myappbar.databinding.ActivityMainBinding
import com.dicoding.myappbar.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}