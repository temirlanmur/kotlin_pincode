package com.example.pincodepage

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val numberButtons = mutableListOf<Button>()
    private lateinit var btnDelete: Button
    private lateinit var btnOk: Button

    private lateinit var pinCodeTextView: TextView

    private var pinCodeStringBuilder: StringBuilder = java.lang.StringBuilder(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        updatePinCodeText()

        setClickListeners()
    }

    private fun initViews() {
        for (i in 0..9) {
            val btn: Button =
                findViewById(resources.getIdentifier("btn_${i}", "id", packageName))
            numberButtons.add(btn)
        }

        btnDelete = findViewById(R.id.btn_delete)
        btnOk = findViewById(R.id.btn_ok)

        pinCodeTextView = findViewById(R.id.pin_code)
    }

    private fun updatePinCodeText() {
        if (pinCodeStringBuilder.isEmpty()) {
            pinCodeTextView.text = resources.getString(R.string.hint_text)
            return
        }
        pinCodeTextView.text = pinCodeStringBuilder.toString()
    }

    private fun setClickListeners() {
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (pinCodeStringBuilder.length >= 4) {
                    return@setOnClickListener
                }
                pinCodeStringBuilder.append(index)
                updatePinCodeText()
            }
        }

        btnDelete.setOnClickListener {
            pinCodeStringBuilder.deleteLast()
            updatePinCodeText()
        }

        btnOk.setOnClickListener {
            if (pinCodeStringBuilder.toString() == resources.getString(R.string.correct_pass)) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.success_toast),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}