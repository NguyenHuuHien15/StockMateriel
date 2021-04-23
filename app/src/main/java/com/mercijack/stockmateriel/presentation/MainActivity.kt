package com.mercijack.stockmateriel.presentation

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.mercijack.stockmateriel.R
import com.mercijack.stockmateriel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var _dataBinding: ActivityMainBinding? = null
    val dataBinding get() = _dataBinding!!
    private val viewModel: MainViewModel by viewModels()

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _dataBinding = ActivityMainBinding.inflate(layoutInflater)
        dataBinding.viewModel = viewModel
        setContentView(dataBinding.root)

        setSupportActionBar(dataBinding.toolbar)

        dataBinding.navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navController.navigate(R.id.action_to_Home)
                R.id.navigation_notifications -> navController.navigate(R.id.action_to_Notifications)
            }
            true
        }

        viewModel.isFullScreen.observe(this, {
            if(it == true) doFullScreen() else exitFullScreen()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun exitFullScreen() {
        supportActionBar?.show()
        dataBinding.navView.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    private fun doFullScreen() {
        supportActionBar?.hide()
        dataBinding.navView.visibility = View.GONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

}