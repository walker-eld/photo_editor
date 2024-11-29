package com.walkersilva.trainingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.walkersilva.trainingapp.databinding.ActivityFeatureBinding

class FeatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttonName = intent.getStringExtra("button_name")
        buttonName?.let {
            title = getString(R.string.feature_activity_title, it)
            binding.textView.text = it
        }
    }
}
