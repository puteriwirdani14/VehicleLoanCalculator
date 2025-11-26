package com.example.vehicleloancalculator

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Setup toolbar with back button
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_about)

        toolbar.setNavigationOnClickListener {
            finish()  // ← go back
        }

        // Handle GitHub link click
        val tvGithub = findViewById<TextView>(R.id.tvGithub)
        tvGithub.setOnClickListener {
            val url = "https://github.com/puteriwirdani14/VehicleLoanCalculator"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}