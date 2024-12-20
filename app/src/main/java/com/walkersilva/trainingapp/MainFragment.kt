package com.walkersilva.trainingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.walkersilva.trainingapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            binding.imageView.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                val intent = Intent(requireContext(), FeatureFragment::class.java).apply {
                    putExtra("button_name", label)
                }
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
