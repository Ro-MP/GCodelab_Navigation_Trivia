package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavAction
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    lateinit var binding: FragmentTitleBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<FragmentTitleBinding>(
            inflater, R.layout.fragment_title, container, false)

        binding.playButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setupMenu()
    }








/*

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.navdrawer_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.
                onNavDestinationSelected(menuItem, requireView().findNavController())
            }

        })
    }


 */






}