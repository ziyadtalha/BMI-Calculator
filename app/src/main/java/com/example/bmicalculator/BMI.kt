package com.example.bmicalculator

class BMI (private var height: Double, private var weight: Double) {
    private var value: Double
    private var health: String = ""

    private fun findHealth()
    {
        if (value < 16.5){
            health = "Severely Underweight!"
        }
        else{
            if (value < 18.5){
                health = "Underweight!"
            }
            else{
                if ((value >= 18.5) && (value <= 24.9)){
                    health = "Healthy!"
                }
                else{
                    if ((value >= 25) && (value <= 29.9)){
                        health = "Overweight!"
                    }
                    else{
                        health = "Obese!"
                    }
                }
            }
        }
    }

    init {
        //height in meters
        val heightInMetres = (height / 100)

        value = weight / (heightInMetres * heightInMetres)

        findHealth()
    }

    fun getBMI(): Double
    {
        return value
    }

    fun getHealth(): String
    {
        return health
    }
}