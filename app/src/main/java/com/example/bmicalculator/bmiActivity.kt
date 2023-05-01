package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bmicalculator.databinding.BmiActivityBinding

class bmiActivity : AppCompatActivity() {

    private lateinit var binding: BmiActivityBinding
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id").toString()

        binding = BmiActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        val  button = findViewById<Button>(binding.bmiButton.id)

        button.setOnClickListener()
        {
            binding.results.text = ""
            binding.healthy.text = ""

            val bmi = calculate()

            binding.results.text = String.format("Your BMI is: %.2f", bmi.getBMI())
            binding.healthy.text = String.format("Considered: %s", bmi.getHealth())

        }
    }

    private fun calculate() :BMI
    {
        val height = binding.heightPicker.value.toDouble()
        val weight = binding.weightPicker.value.toDouble()

        return BMI(height, weight, id.toInt())
    }
}