package com.example.vehicleloancalculator

import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // ⭐ Clickable Github Link
        val tvGithub = findViewById<TextView>(R.id.tvGithub)

        tvGithub.setOnClickListener {
            val url = "https://github.com/yourusername/yourrepository"  // <-- change this
            val intent = android.content.Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            startActivity(intent)
        }
    }
}
