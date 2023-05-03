package com.example.bmicalculator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var context:Context = this

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadInter()

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

        val adRequest = AdRequest.Builder().build()
        MobileAds.initialize(context) {}
        val mAdView: AdView = findViewById<com.google.android.gms.ads.AdView>(binding.adView.id)
        mAdView.loadAd(adRequest)

        InterstitialAd.load(context, "ca-app-pub-3940256099942544/1033173712", adRequest, object: InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd?.show(context as Activity)
            }

        })

        //mInterstitialAd?.show(this)

    }

    private fun loadInter(){
        if (mInterstitialAd != null){
            mInterstitialAd?.show(this)
        }
        else{
            Log.d("TAG", "Ad was monki!")
        }
    }
}