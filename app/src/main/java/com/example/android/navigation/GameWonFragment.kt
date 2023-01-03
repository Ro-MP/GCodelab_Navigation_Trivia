/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener {
            view?.findNavController()
                ?.navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        setupMenu()

        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val text = "NumCorrect: ${args.numCorrect} \nQuestions answered: ${args.questionsAnswered}"
        Toast.makeText(context, text, Toast.LENGTH_LONG)
            .show()

        return binding.root
    }


    // Build implicit Intent to share the score
    private fun getShareintent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.questionsAnswered))
        return shareIntent
    }

    // Run the Intent
    private fun shareSucces(): Boolean {
        startActivity(getShareintent())
        return true
    }


    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.winner_menu, menu)
                // ResolveActivity - Make sure the shareIntent resolves to an Activity
                if (getShareintent().resolveActivity(requireActivity().packageManager) == null) {
                    menu.findItem(R.id.share).isVisible = false
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.share -> shareSucces()
                }

                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }




}


