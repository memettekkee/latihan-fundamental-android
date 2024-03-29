package com.dicoding.githubuserlist.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuserlist.R
import com.dicoding.githubuserlist.databinding.ActivityThemeBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding
    private val themeViewModel by viewModels<ThemeViewModel> {
        ThemeViewModel.Factory(SettingPreferences(dataStore))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        themeViewModel.getThemeSettings().observe(this) { themeChanged: Boolean ->
            if (themeChanged) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked: Boolean ->
            themeViewModel.saveThemeSetting(isChecked)
        }
    }
}