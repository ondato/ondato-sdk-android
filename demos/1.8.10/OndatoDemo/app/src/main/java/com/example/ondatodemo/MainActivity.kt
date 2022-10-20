package com.example.ondatodemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.ondatodemo.databinding.ActivityMainBinding
import com.ondato.sdk.Ondato
import com.ondato.sdk.OndatoConfig
import com.ondato.sdk.OndatoError
import com.ondato.sdk.enums.Language
import com.ondato.sdk.enums.LivenessCheck

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = OndatoConfig.Builder()
            .setCredentials("YOUR_USERNAME", "YOUR_PASSWORD")
            .showStartScreen(true) //default is true
            .showConsentScreen(true) //default is true
            .showSelfieWithDocumentScreen(true) //default is true
            .showSuccessScreen(true) //default is true
            .recordProcess(true) //default is true
            .setRemoveSelfieFrame(false) // default is false (only for passive liveness)
            .setMode(OndatoConfig.Mode.TEST) //default is TEST
            .setLanguage(Language.English) // default is English, supports System (is in English if not translated)
            .setLivenessCheckMode(LivenessCheck.Active) //Choose Active or Passive, default is Active
            .setDriverLicenseBacksideRequired(true) //default is false
            .build()

        Ondato.init(config)
        Ondato.starIdentification(applicationContext, object : Ondato.ResultListener {
            override fun onSuccess(identificationId: String?) {
                Log.d("OndatoDemo", "Success!")
            }

            override fun onFailure(identificationId: String?, error: OndatoError) {
                Log.d("OndatoDemo", "Failure!")
            }
        })
    }
}