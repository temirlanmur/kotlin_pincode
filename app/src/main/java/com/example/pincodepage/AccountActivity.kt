package com.example.pincodepage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {
    private lateinit var btnShare: Button
    private lateinit var btnMail: Button
    private lateinit var btnCall: Button
    private lateinit var btnCamera: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        initViews()

        setClickListeners()
    }

    private fun initViews() {
        btnShare = findViewById(R.id.btn_share)
        btnMail = findViewById(R.id.btn_mail)
        btnCall = findViewById(R.id.btn_call)
        btnCamera = findViewById(R.id.btn_camera)
    }

    private fun setClickListeners() {
        btnShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, "text to share")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        btnMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
            }
            startActivity(intent)
        }

        btnCall.setOnClickListener {
            val number = Uri.parse("tel:$SAMPLE_PHONE_NUMBER")
            val intent = Intent(Intent.ACTION_DIAL, number)
            startActivity(intent)
        }

        btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
    }
}