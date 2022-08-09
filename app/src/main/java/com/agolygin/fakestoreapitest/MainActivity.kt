package com.agolygin.fakestoreapitest

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.agolygin.fakestoreapitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        (application as FakestoreApiTestApplication).appComponent.inject(viewModel)

        val navView: BottomNavigationView = binding.navView

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_search, R.id.navigation_favourites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewModel.getProductsData()
    }

    fun showNavigationBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.navView.visibility = View.VISIBLE
    }

    fun hideNavigationBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.fragments.size > 0) {
            val fragment : Fragment = supportFragmentManager.fragments[0]
            if (fragment is NavHostFragment && fragment.childFragmentManager.backStackEntryCount > 0) {
                fragment.childFragmentManager.popBackStack()
            }
            return true
        }
        return false
    }
}