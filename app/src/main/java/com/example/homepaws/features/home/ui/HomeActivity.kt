package com.example.homepaws.features.home.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homepaws.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HomePaws)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
    }
}