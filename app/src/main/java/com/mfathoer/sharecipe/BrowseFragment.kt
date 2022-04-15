package com.mfathoer.sharecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mfathoer.sharecipe.databinding.FragmentBrowseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseFragment : Fragment() {

    private var _binding : FragmentBrowseBinding? = null

    private val binding get() = _binding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBrowseBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}