package com.geeks.youtubeapi.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.geeks.youtubeapi.utils.Resource
import com.geeks.youtubeapi.utils.showToast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
        initClickListeners()
        getArgs()
        navigateToPlaylistFragment()
    }

    protected open fun getArgs() {}

    protected open fun initClickListeners() {}

    protected open fun setupViews() {}

    protected open fun setupObservers() {}

    protected open fun navigateToPlaylistFragment() {}

    protected fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: (Resource<T>) -> Unit
    ) {
        this@stateHandler.observe(viewLifecycleOwner) { result ->
            state(result)
            when (result) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    success(result.data)
                }
            }
        }
    }

    protected fun <T> LiveData<Resource<T>>.handleResource(
        isLoading: (Boolean) -> Unit,
        onSuccess: (T?) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { resource ->
            isLoading.invoke(resource is Resource.Loading)
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    onSuccess(resource.data)
                }

                is Resource.Error -> {
                    showToast(resource.message)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}