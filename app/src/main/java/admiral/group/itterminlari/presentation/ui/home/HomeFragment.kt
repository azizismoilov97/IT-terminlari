package admiral.group.itterminlari.presentation.ui.home

import admiral.group.itterminlari.R
import admiral.group.itterminlari.presentation.util.Helper
import admiral.group.itterminlari.databinding.FragmentHomeBinding
import admiral.group.itterminlari.presentation.ui.home.adapter.HomeAdapter
import admiral.group.itterminlari.presentation.ui.main.MainActivity
import admiral.group.itterminlari.presentation.viewmodel.MainViewModel
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}
    private val viewModel: MainViewModel by viewModels()
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private  lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           with(viewBinding){


            goTelegram.setOnClickListener {
                   Helper.connectViaTelegram(requireContext())
               }

            viewModel.getTermins()

            viewModel.listTermins.observe(viewLifecycleOwner){ list ->

               homeAdapter= HomeAdapter(HomeAdapter.OnClickListener{
                   viewModel.updateTermin(it)
               }, HomeAdapter.OnItemClickListener {
                   navController.navigate(HomeFragmentDirections.actionHomeFragmentToDescriptionFragment(it))
                   (requireActivity() as MainActivity).setGone()
               })

               homeAdapter.myTerminList=list

               myRecyclerView.apply {
                   layoutManager= LinearLayoutManager(requireContext())
                   adapter=homeAdapter
               }

           }

            txtInputEditText.setOnEditorActionListener { _, actionId, _ ->
                   if (actionId == EditorInfo.IME_ACTION_DONE) {
                      // do something
                   }
                   true
               }

        }

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).setVisible()
    }

}

