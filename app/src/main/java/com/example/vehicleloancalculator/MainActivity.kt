package com.example.vehicleloancalculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ⭐ Top Toolbar
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(topAppBar)

        // ⭐ Input fields
        val etPrice = findViewById<EditText>(R.id.etVehiclePrice)
        val etDown = findViewById<EditText>(R.id.etDownPayment)
        val etYears = findViewById<EditText>(R.id.etLoanPeriod)
        val etRate = findViewById<EditText>(R.id.etInterestRate)

        // ⭐ Buttons
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // ⭐ Output fields
        val tvLoanAmount = findViewById<TextView>(R.id.tvLoanAmount)
        val tvTotalInterest = findViewById<TextView>(R.id.tvTotalInterest)
        val tvTotalPayment = findViewById<TextView>(R.id.tvTotalPayment)
        val tvMonthlyPayment = findViewById<TextView>(R.id.tvMonthlyPayment)

        // ================================
        // 🔢 CALCULATION LOGIC
        // ================================
        btnCalculate.setOnClickListener {
            val price = etPrice.text.toString().toDoubleOrNull()
            val down = etDown.text.toString().toDoubleOrNull()
            val years = etYears.text.toString().toIntOrNull()
            val rate = etRate.text.toString().toDoubleOrNull()

            // Input validation
            if (price == null || down == null || years == null || rate == null) {
                tvLoanAmount.text = "RM0.00"
                tvTotalInterest.text = "RM0.00"
                tvTotalPayment.text = "RM0.00"
                tvMonthlyPayment.text = "RM0.00"
                return@setOnClickListener
            }

            // Calculation
            val loanAmount = price - down
            val totalInterest = loanAmount * (rate / 100) * years
            val totalPayment = loanAmount + totalInterest
            val monthlyPayment = totalPayment / (years * 12)

            // Display results
            tvLoanAmount.text = "RM %.2f".format(loanAmount)
            tvTotalInterest.text = "RM %.2f".format(totalInterest)
            tvTotalPayment.text = "RM %.2f".format(totalPayment)
            tvMonthlyPayment.text = "RM %.2f".format(monthlyPayment)
        }

        // ================================
        // 🧹 CLEAR BUTTON
        // ================================
        btnClear.setOnClickListener {
            etPrice.text.clear()
            etDown.text.clear()
            etYears.text.clear()
            etRate.text.clear()

            tvLoanAmount.text = "RM0.00"
            tvTotalInterest.text = "RM0.00"
            tvTotalPayment.text = "RM0.00"
            tvMonthlyPayment.text = "RM0.00"
        }
    }

    // ================================
    // 📌 MENU (Home & About)
    // ================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_home -> true

            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
