package com.github.luecy1.holodex

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.github.luecy1.holodex.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment

        controller = navHostFragment.navController
        controller.addOnDestinationChangedListener { controller, destination, _ ->
            Timber.d(destination.label.toString())

            val detailFragmentDestination = controller.graph.findNode(R.id.hololiverDetailFragment)
                ?: return@addOnDestinationChangedListener
            if (detailFragmentDestination == destination) {
                binding.tabLayout.visibility = View.GONE
            } else {
                binding.tabLayout.visibility = View.VISIBLE
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab ?: return

                val position = tab.position
                when (position) {
                    0 -> {
//                        controller.navigate(R.id.action_itemFragment_to_item2Fragment)
                    }
                    1 -> {
                        Timber.d("en change")
                        val bundle = bundleOf("kind" to "EN")
//                        controller.navigate(R.id.action_item2Fragment_to_itemFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
