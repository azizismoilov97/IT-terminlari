package admiral.group.itterminlari.presentation.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentInfoBinding
import admiral.group.itterminlari.presentation.viewmodel.MainViewModel
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoFragment : Fragment(R.layout.fragment_info) {

    private val viewBinding: FragmentInfoBinding by viewBinding(FragmentInfoBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){
            txtFooter.setOnClickListener { telegramJoin() }
        }
    }
    private fun telegramJoin() {
        gotoUrl("https://play.google.com/store/apps/dev?id=5271774247652006112")
    }

    private fun gotoUrl(s: String) {
        val uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

}