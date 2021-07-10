package com.tubetoast.chucknorrisjokes.ui.fragments.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.tubetoast.chucknorrisjokes.R
import com.tubetoast.chucknorrisjokes.databinding.FragmentJokesBinding
import com.tubetoast.chucknorrisjokes.viewmodel.jokes.AppState
import com.tubetoast.chucknorrisjokes.viewmodel.jokes.JokesViewModel

class JokesFragment : Fragment() {

    private var _binding : FragmentJokesBinding? = null
    private val binding : FragmentJokesBinding get() = _binding!!

    private val viewModel by activityViewModels<JokesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentJokesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        setupRecyclerView()
        setupProgressBar()
    }

    private fun setupButton() {
        binding.reloadButton.setOnClickListener {
            try {
                val count = binding.countInput.text.toString().toInt()
                viewModel.loadJokes(count)
            } catch (e: NumberFormatException) {
                Snackbar.make(binding.root, getString(R.string.number_error), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = JokesAdapter()
        binding.jokesList.adapter = adapter
        viewModel.getJokes().observe(viewLifecycleOwner) {
            adapter.jokes = it
        }
    }

    private fun setupProgressBar() {
        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                AppState.Loading -> {
                    binding.progressBar?.visibility = View.VISIBLE
                }
                AppState.Success -> {
                    binding.progressBar?.visibility = View.GONE
                }
                is AppState.Error -> {
                    binding.progressBar?.visibility = View.GONE
                    Snackbar.make(binding.root, getString(R.string.internet_error), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}