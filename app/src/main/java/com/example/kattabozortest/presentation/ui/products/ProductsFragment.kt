package com.example.kattabozortest.presentation.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kattabozortest.databinding.FragmentProductsBinding
import com.example.kattabozortest.presentation.ui.products.adapters.ProductsAdapter
import com.example.kattabozortest.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapterProducts: ProductsAdapter
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initData()
    }

    private fun initData() {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {

                productsViewModel.products.collectLatest { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            binding.progressCircular.visibility = View.GONE
                            result.data?.let { products -> adapterProducts.submitList(products.offers) }
                        }
                        is ResultWrapper.Error -> {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "${result.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is ResultWrapper.Loading -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }
        productsViewModel.getProducts()
    }

    private fun initRecycler() {
        adapterProducts = ProductsAdapter()
        binding.listProducts.apply {
            adapter = adapterProducts
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}