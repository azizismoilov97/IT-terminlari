package admiral.group.itterminlari.ui.description

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentDescriptionBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.fragment_description.*
import java.util.*
import kotlin.math.abs


@AndroidEntryPoint
class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private val viewBinding: FragmentDescriptionBinding by viewBinding(FragmentDescriptionBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val viewModel: DescriptionViewModel by viewModels()
    private val args: DescriptionFragmentArgs by navArgs()
    private var wordTxt: String=" "

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            requireActivity().setActionBar(toolbar)
            requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
            requireActivity().actionBar?.setDisplayShowHomeEnabled(true)

            viewModel.readTermin(args.id).observe(viewLifecycleOwner) { termin ->

                if (termin != null) {
                    wordTxt=termin.word
                    word.text = termin.word.uppercase(Locale.ROOT)
//                    contentLayout.textDescription.text=termin.description.replaceFirstChar { it.uppercase() }
                }
            }

            appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->

                if(abs(verticalOffset) - appBarLayout.totalScrollRange == 0){
                    toolbarLayout.title=wordTxt.uppercase()
                }else{
                    toolbarLayout.title=" "
                }

            }



            toolbar.setNavigationOnClickListener {
                navController.navigateUp()
            }

        }
    }

}