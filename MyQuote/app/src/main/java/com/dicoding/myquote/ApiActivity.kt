package com.dicoding.myquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.myquote.databinding.ActivityApiBinding

class ApiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentByTag(TesApiFragment::class.java.simpleName)

        if (fragment !is TesApiFragment) {
            Log.d("MyQuote", "Fragment Name :" + TesApiFragment::class.java.simpleName)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.tes_api_container, TesApiFragment(), TesApiFragment::class.java.simpleName)
                .commit()
        }
    }
}