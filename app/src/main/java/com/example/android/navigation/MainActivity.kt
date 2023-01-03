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

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.android.navigation.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {


    private var isBackPressed = false
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // initialize drawerLayout
        drawerLayout = binding.drawerLayout

        // find navigation controller object
        val navController = this.findNavController(R.id.nav_host_fragment)
        // Link navigation controller to the app bar
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        // Connect the navigation drawer to the navigation controller so when the users select
        //      items in navigation drawer, the app navigates to the appropriate Fragment
        NavigationUI.setupWithNavController(binding.navView, navController)


        // BackButton
        /*
        val backPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                 Toast.makeText(this@MainActivity, "Holis", Toast.LENGTH_SHORT).show()
            }
        }
        onBackPressedDispatcher.addCallback(this, backPressedCallback)

         */


        // Implementacion de Menu en AppBar
        /*
        addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }
0
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            }
        })
         */
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }



}
