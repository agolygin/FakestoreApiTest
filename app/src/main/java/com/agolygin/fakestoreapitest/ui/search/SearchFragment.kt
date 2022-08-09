package com.agolygin.fakestoreapitest.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agolygin.fakestoreapitest.MainViewModel
import com.agolygin.fakestoreapitest.R
import com.agolygin.fakestoreapitest.databinding.FragmentSearchBinding
import com.agolygin.fakestoreapitest.ui.ProductListAdapter
import com.agolygin.fakestoreapitest.ui.details.ProductInfoFragment
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import com.agolygin.fakestoreapitest.data.ProductData
import androidx.core.content.ContextCompat.getSystemService


class SearchFragment : Fragment() {

    private lateinit var activityViewModel: MainViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    val onItemClicked : (ProductData) -> Unit = {pd ->
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, ProductInfoFragment.newInstance(pd))
            .addToBackStack("product_info_fragment")
            .commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        activityViewModel.products.observe(viewLifecycleOwner, Observer{
            binding.productList.layoutManager = LinearLayoutManager(binding.productList.context)
            binding.productList.adapter = ProductListAdapter(it, onItemClicked)
        })

        binding.searchEditText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val search = binding.searchEditText.text.toString().lowercase()
                val filtered = ArrayList<ProductData>()
                val iterator = activityViewModel.products.value?.iterator()
                iterator?.let {
                    while (it.hasNext()) {
                        val pd = it.next()
                        if(pd.title.lowercase().contains(search) || pd.description.lowercase().contains(search)) {
                            filtered.add(pd)
                        }
                    }
                    binding.productList.adapter = ProductListAdapter(filtered, onItemClicked )

                    val imm = getSystemService(requireContext(), InputMethodManager::class.java)
                    imm?.hideSoftInputFromWindow(binding.productList.windowToken, 0)

                    return@OnEditorActionListener true
                }
            }
            false
        })
    }

    override fun onResume() {
        super.onResume()
        binding.searchEditText.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}