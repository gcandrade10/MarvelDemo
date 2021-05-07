package com.example.marveldemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.marveldemo.databinding.FragmentCharacterDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val characterDetailViewModel: CharacterDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMainLiveDataObserver()
        setUpErrorLiveDataObserver(view)
    }

    private fun setUpErrorLiveDataObserver(view: View) {
        characterDetailViewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding.loadingIndicator.isVisible = false
            Toast.makeText(view.context, R.string.general_error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpMainLiveDataObserver() {
        characterDetailViewModel.mainLiveData.observe(viewLifecycleOwner, {
            binding.detailDescription.text = it.description
            binding.detailTitle.text = it.name
            Glide.with(this).load(it.image).centerCrop().into(binding.detailImage)
            binding.loadingIndicator.isVisible = false
        })
        characterDetailViewModel.getCharacter(args.id)
    }

}