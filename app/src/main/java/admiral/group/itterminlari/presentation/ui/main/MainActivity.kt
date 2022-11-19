package admiral.group.itterminlari.presentation.ui.main

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController(R.id.nav_host_fragment_activity_main)}

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    var selectedItemOrder = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showToast(selectedItemOrder)

        binding.bottomNavigationView.setOnItemSelectedListener { id ->

            selectedItemOrder = when (id) {
                R.id.homeFragment -> selectedItemOrder
                R.id.savedFragment -> 2
                R.id.infoFragment -> 3
                else -> {
                    0
                }
            }

            showToast(selectedItemOrder)

        }

        // onbackpressed
        onBackPressedDispatcher.addCallback(this,  object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedItemOrder == 0 || selectedItemOrder == 1) {
                    finish()
                } else {
                    binding.bottomNavigationView.setItemSelected(R.id.homeFragment, true)
                    selectedItemOrder = 1
                }
            }
        })
    }

    fun setVisible(){
        binding.bottomNavigationView.visibility=View.VISIBLE
    }

    fun setGone(){
        binding.bottomNavigationView.visibility=View.GONE
    }

    private fun showToast(order: Int) {
        Toast.makeText(this, "$order", Toast.LENGTH_SHORT).show()
    }

}