package admiral.group.itterminlari.presentation.ui.main

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            bottomNavigationView.setupWithNavController(navController)
        }
    }

    fun setVisible(){
        binding.bottomNavigationView.visibility=View.VISIBLE
    }

    fun setGone(){
        binding.bottomNavigationView.visibility=View.GONE
    }

}