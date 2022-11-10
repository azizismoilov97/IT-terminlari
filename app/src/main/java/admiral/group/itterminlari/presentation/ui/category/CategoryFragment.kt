package admiral.group.itterminlari.presentation.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentCategoryBinding
import admiral.group.itterminlari.presentation.viewmodel.MainViewModel
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {


    private  val viewModel: MainViewModel by viewModels()
    private  val viewBinding: FragmentCategoryBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){

        }
    }

}