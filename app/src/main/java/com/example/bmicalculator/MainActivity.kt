package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val button = findViewById<Button>(binding.idButton.id)

        button.setOnClickListener()
        {
            val id = binding.enterID.text.toString()
            if (id != "")
            {
                if (id.toInt() > 0)
                {
                    val intent = Intent(this, bmiActivity::class.java)

                    //passing data to our new activity.
                    intent.putExtra("id", id)

                    //starting a new activity.
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Invalid ID!", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this, "ID cannot be empty!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}