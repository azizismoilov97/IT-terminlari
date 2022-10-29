package admiral.group.itterminlari.ui.home

import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}
    private val viewModel: HomeViewModel by viewModels()
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private  lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           with(viewBinding){

           viewModel.readTermins().observe(viewLifecycleOwner){ list ->

               homeAdapter= HomeAdapter(HomeAdapter.OnClickListener{
                   viewModel.updateProject(it)
               }, HomeAdapter.OnItemClickListener {
                   navController.navigate(HomeFragmentDirections.actionHomeFragmentToDescriptionFragment(it))
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

}

