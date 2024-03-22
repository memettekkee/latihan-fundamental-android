package com.dicoding.githubuserlist.detailuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserlist.R
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)

        val followViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)

        followViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }

        arguments?.let {
            val position = it.getInt(ARG_SECTION_NUMBER)
            val username = it.getString(ARG_NAME)!!

            if (position == 1){
                followViewModel.getFollowersUser(username)
                followViewModel.userFollowers.observe(requireActivity()){
                    setFollowers(it)
                }
            } else {
                followViewModel.getFollowingUser(username)
                followViewModel.userFollowing.observe(requireActivity()) {
                    setFollowing(it)
                }
            }
        }
    }
    private fun setFollowers(followersData: List<UserItems>) {
        val adapter = UserListAdapter()
        adapter.submitList(followersData)
        binding.rvFollow.adapter = adapter
    }
    private fun setFollowing(followingData: List<UserItems>) {
        val adapter = UserListAdapter()
        adapter.submitList(followingData)
        binding.rvFollow.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar3.visibility = View.VISIBLE
        } else {
            binding.progressBar3.visibility = View.GONE
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_NAME = "app_name"
    }
}