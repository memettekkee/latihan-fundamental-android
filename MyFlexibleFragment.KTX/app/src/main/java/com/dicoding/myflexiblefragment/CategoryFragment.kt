package com.dicoding.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.dicoding.myflexiblefragment.databinding.FragmentCategoryBinding
import com.dicoding.myflexiblefragment.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match

class CategoryFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentCategoryBinding?  = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDetailCategory.setOnClickListener (this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_detail_category) {
            val detailCategoryFragment = DetailCategoryFragment()

            val bundle = Bundle()
            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            detailCategoryFragment.arguments = bundle
            detailCategoryFragment.description = description

            val fragmentManager = parentFragmentManager

            fragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
            }

//            fragmentManager?.beginTransaction()?.apply {
//                replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
//                addToBackStack(null)
//                commit()
//            }
        }
    }
}