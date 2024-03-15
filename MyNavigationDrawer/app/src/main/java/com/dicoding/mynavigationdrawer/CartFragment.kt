package com.dicoding.mynavigationdrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.mynavigationdrawer.databinding.FragmentCartBinding
import com.dicoding.mynavigationdrawer.databinding.FragmentSlideshowBinding

class CartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}