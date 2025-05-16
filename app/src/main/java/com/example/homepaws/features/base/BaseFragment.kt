package com.example.homepaws.features.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Muhamed Amin Hassan on 13,May,2025
 * @see <a href="https://github.com/muhamedamin308">Muhamed's Github</a>,
 * Egypt, Cairo.
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun inflateBinding(layoutInflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflateBinding(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun onViewReady() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
    }

}