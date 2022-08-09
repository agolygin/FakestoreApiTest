package com.agolygin.fakestoreapitest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agolygin.fakestoreapitest.MainViewModel
import com.agolygin.fakestoreapitest.R
import com.agolygin.fakestoreapitest.databinding.FragmentMainBinding
import com.agolygin.fakestoreapitest.ui.ProductListAdapter
import com.agolygin.fakestoreapitest.ui.details.ProductInfoFragment

class MainFragment : Fragment() {

    private lateinit var activityViewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        activityViewModel.products.observe(viewLifecycleOwner, Observer{
            val productList: RecyclerView = binding.productList
            productList.layoutManager = LinearLayoutManager(productList.context)
            productList.adapter = ProductListAdapter(it) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, ProductInfoFragment.newInstance(it))
                    .addToBackStack("product_info_fragment")
                    .commit()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}