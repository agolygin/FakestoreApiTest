package com.agolygin.fakestoreapitest.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agolygin.fakestoreapitest.MainActivity
import com.agolygin.fakestoreapitest.R
import com.agolygin.fakestoreapitest.data.ProductData
import com.agolygin.fakestoreapitest.databinding.FragmentProductInfoBinding
import com.squareup.picasso.Picasso

private const val ARG_PARAM_PRODUCT = "param_product"

class ProductInfoFragment : Fragment() {
    private var productData: ProductData? = null

    private var _binding: FragmentProductInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productData = it.getParcelable(ARG_PARAM_PRODUCT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProductInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideNavigationBar()

        binding.textTitle.text = productData?.title
        binding.textDescription.text = productData?.description
        binding.textPrice.text = getString(R.string.text_price, productData?.price)

        Picasso.get().load(productData?.image).into(binding.imageView);
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).showNavigationBar()
    }

    companion object {
        @JvmStatic
        fun newInstance(product : ProductData) =
            ProductInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_PRODUCT, product)
                }
            }
    }
}