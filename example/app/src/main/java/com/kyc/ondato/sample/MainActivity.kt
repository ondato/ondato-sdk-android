package com.kyc.ondato.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ondato.sdk.Ondato
import com.ondato.sdk.OndatoConfig
import com.ondato.sdk.OndatoError


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSdk()
        findViewById<View>(R.id.start_button)?.setOnClickListener { startIdentification() }
    }

    private fun initSdk() {
        val config = OndatoConfig.Builder()
            .setIdentityVerificationId("<Your identification id here>")
            .showStartScreen(true)
            .showSuccessScreen(true)
            .setMode(OndatoConfig.Mode.TEST)
            .setLanguage("en")
            .build()

        Ondato.INSTANCE.init(config)
    }

    private fun startIdentification() {
        Ondato.INSTANCE.startIdentification(applicationContext, object : Ondato.ResultListener {
            override fun onSuccess(identificationId: String?) {
                findViewById<TextView>(R.id.result_text_view)?.text = "Success"
            }

            override fun onFailure(identificationId: String?, error: OndatoError) {
                findViewById<TextView>(R.id.result_text_view)?.text = "Failed"
            }
        })
    }
}