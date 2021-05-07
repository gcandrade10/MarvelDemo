package com.example.marveldemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemo.databinding.FragmentCharacterListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMainLiveDataObserver()
        setUpErrorLiveDataObserver(view)
    }

    private fun setUpErrorLiveDataObserver(view: View) {
        characterListViewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding.loadingIndicator.isVisible = false
            Toast.makeText(view.context, R.string.general_error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpMainLiveDataObserver() {
        characterListViewModel.mainLiveData.observe(viewLifecycleOwner, {
            val adapter = CharacterAdapter(it) { character ->
                val action =
                    CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                        character.id
                    )
                NavHostFragment.findNavController(this).navigate(action)
            }
            adapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.characters.layoutManager = LinearLayoutManager(activity)
            binding.characters.adapter = adapter
            binding.loadingIndicator.isVisible = false
        })
    }

}