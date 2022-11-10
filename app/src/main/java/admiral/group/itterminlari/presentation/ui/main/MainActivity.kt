package admiral.group.itterminlari.presentation.ui.main

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.ActivityMainBinding
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

            bottomNavigationView.background = null

            bottomNavigationView.menu.getItem(2).isEnabled = false

            bottomNavigationView.setupWithNavController(navController)


//            bottomNavigationView.setOnItemSelectedListener {
//
//                when(it.itemId){
//                    R.id.homeFragment ->{
//                        navController.setGraph(R.navigation.home_navigation)
//                    }
//                    R.id.categoryFragment ->{
//                        navController.setGraph(R.navigation.category_nav)
//                    }
//                    R.id.placeholder ->{
//
//                    }
//                    R.id.savedFragment ->{
//                        navController.setGraph(R.navigation.saved_nav)
//                    }
//                    R.id.infoFragment ->{
//                        navController.setGraph(R.navigation.info_nav)
//                    }
//                }
//
//                return@setOnItemSelectedListener true
//            }

            fab.setOnClickListener {
             switchToTelegram()
            }

        }

    }

    private fun switchToTelegram() {
        val uri = Uri.parse("https://t.me/it_terminlar")
        val rateUs = Intent(Intent.ACTION_VIEW, uri)
        startActivity(rateUs)
    }

}