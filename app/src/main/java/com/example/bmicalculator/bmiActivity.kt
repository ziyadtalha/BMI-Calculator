package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.bmicalculator.databinding.BmiActivityBinding

class bmiActivity : AppCompatActivity() {

    private lateinit var binding: BmiActivityBinding
    private lateinit var id: String
    private lateinit var bmi: BMI
    private val dbManager = bmiDbManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id").toString()

        binding = BmiActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        val  calculateButton = findViewById<Button>(binding.bmiButton.id)

        calculateButton.setOnClickListener()
        {
            binding.results.text = ""
            binding.healthy.text = ""

            bmi = calculate()

            binding.results.text = String.format("Your BMI is: %.2f", bmi.getBMI())
            binding.healthy.text = String.format("Considered: %s", bmi.getHealth())

        }

        val updateButton = findViewById<Button>(binding.updateButton.id)

        updateButton.setOnClickListener()
        {
            //we only update if a new BMI has been calculated
            if (this::bmi.isInitialized)
            {
                if(dbManager.insert(bmi) == true)
                {
                    Toast.makeText(this, "Inserted!", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "No BMI Calculated!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculate() :BMI
    {
        val height = binding.heightPicker.value.toDouble()
        val weight = binding.weightPicker.value.toDouble()

        return BMI(height, weight, id.toInt())
    }
}