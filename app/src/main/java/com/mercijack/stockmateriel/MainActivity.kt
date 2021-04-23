package com.mercijack.stockmateriel

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mercijack.stockmateriel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityMainBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        setSupportActionBar(dataBinding.toolbar)

        dataBinding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.action_to_Home)
                R.id.navigation_notifications -> navController.navigate(R.id.action_to_Notifications)
            }
            true
        }
        dataBinding.navView.selectedItemId = R.id.navigation_home
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun displayBottomNav() {
        dataBinding.navView.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        dataBinding.navView.visibility = View.GONE
    }

}