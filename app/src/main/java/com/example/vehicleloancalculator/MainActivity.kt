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

        // Set up toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Vehicle Loan Calculator"

        // Input fields
        val etPrice = findViewById<EditText>(R.id.etVehiclePrice)
        val etDown = findViewById<EditText>(R.id.etDownPayment)
        val etYears = findViewById<EditText>(R.id.etLoanPeriod)
        val etRate = findViewById<EditText>(R.id.etInterestRate)



        // Buttons
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnClear = findViewById<Button>(R.id.btnClear)

        // Output fields
        val tvLoanAmount = findViewById<TextView>(R.id.tvLoanAmount)
        val tvTotalInterest = findViewById<TextView>(R.id.tvTotalInterest)
        val tvTotalPayment = findViewById<TextView>(R.id.tvTotalPayment)
        val tvMonthlyPayment = findViewById<TextView>(R.id.tvMonthlyPayment)

        // CALCULATION LOGIC
        btnCalculate.setOnClickListener {

            // Validate input fields
            if (etPrice.text.isNullOrEmpty()) {
                etPrice.error = "Please enter vehicle price"
                etPrice.requestFocus()
                return@setOnClickListener
            }

            if (etDown.text.isNullOrEmpty()) {
                etDown.error = "Please enter down payment"
                etDown.requestFocus()
                return@setOnClickListener
            }

            if (etYears.text.isNullOrEmpty()) {
                etYears.error = "Please enter loan period"
                etYears.requestFocus()
                return@setOnClickListener
            }

            if (etRate.text.isNullOrEmpty()) {
                etRate.error = "Please enter interest rate"
                etRate.requestFocus()
                return@setOnClickListener
            }

            // After validation, safely convert values
            val price = etPrice.text.toString().toDouble()
            val down = etDown.text.toString().toDouble()
            val years = etYears.text.toString().toInt()
            val rate = etRate.text.toString().toDouble()

            // Perform calculations
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


        // CLEAR BUTTON
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

    // Inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Handle menu clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}