package com.englishforkids.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.englishforkids.R
import com.englishforkids.mediautils.SoundPlayer
import com.englishforkids.mediautils.Speaker
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val interstitialAd = InterstitialAd(this).apply {
        adUnitId = "ca-app-pub-8029051249946424/4159256842"
        adListener = object : AdListener() {
            override fun onAdClosed() {
                loadInterstitialAd()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        Speaker.initialize()
        SoundPlayer.initialize()
        setupGraph()
    }

    private fun setupGraph() =
        findNavController(
            R.id.navHostFragment
        ).addOnDestinationChangedListener(
            getDisposableListener()
        )

    private fun getDisposableListener() =
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.id == R.id.welcomeFragment)
                    return

                loadBannerAd()
                controller.removeOnDestinationChangedListener(this)
                controller.addOnDestinationChangedListener(getRealListener())
            }
        }


    private fun getRealListener() =
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (interstitialAd.isLoading)
                    return

                if (Random.nextInt(0, 4) == 0)
                    if (interstitialAd.isLoaded)
                        interstitialAd.show()
                    else loadInterstitialAd()
            }
        }

    private fun loadBannerAd() =
        adView.loadAd(
            AdRequest
                .Builder()
                .build()
        )


    private fun loadInterstitialAd() {
        interstitialAd.loadAd(
            AdRequest
                .Builder()
                .build()
        )
    }
}