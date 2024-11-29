package com.walkersilva.trainingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.walkersilva.trainingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.imageView.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLoad.text = getString(R.string.button_load)
        binding.buttonLoad.setOnClickListener {
            getImage.launch("image/*")
        }

        val buttons = listOf(
            binding.buttonCrop to getString(R.string.button_crop),
            binding.buttonLight to getString(R.string.button_light),
            binding.buttonColor to getString(R.string.button_color),
            binding.buttonFilters to getString(R.string.button_filters)
        )

        buttons.forEach { (button, label) ->
            button.text = label
            button.setOnClickListener {
                val intent = Intent(this, FeatureActivity::class.java).apply {
                    putExtra("button_name", label)
                }
                startActivity(intent)
            }
        }
    }
}
