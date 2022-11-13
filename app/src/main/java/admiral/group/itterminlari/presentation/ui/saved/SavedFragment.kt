package admiral.group.itterminlari.presentation.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import admiral.group.itterminlari.R
import admiral.group.itterminlari.presentation.util.Helper
import admiral.group.itterminlari.databinding.FragmentSavedBinding
import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.presentation.ui.home.adapter.HomeAdapter
import admiral.group.itterminlari.presentation.ui.main.MainActivity
import admiral.group.itterminlari.presentation.viewmodel.MainViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {

    private val viewBinding:FragmentSavedBinding by viewBinding(FragmentSavedBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}
    private val viewModel: MainViewModel by viewModels()
    private  lateinit var homeAdapter: HomeAdapter
    private lateinit var myList: MutableList<TerminEntity>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){

            rateApp.setOnClickListener {
                Helper.rateApp(requireContext())
            }
            viewModel.getTermins()
            viewModel.listTermins.observe(viewLifecycleOwner){ list ->
                myList= mutableListOf()
                list.onEach {
                    if (it.bookmark==1){
                       myList.add(it)
                    }
                }
                setUpRecyclerView()
                homeAdapter.myTerminList=myList
            }

        }

    }

    private fun setUpRecyclerView() {
        
        homeAdapter= HomeAdapter(HomeAdapter.OnClickListener{
            viewModel.updateTermin(it)
        }, HomeAdapter.OnItemClickListener {
            (requireActivity() as MainActivity).setGone()
            navController.navigate(SavedFragmentDirections.actionSavedFragment2ToDescriptionFragment(it))
        })

        myRecyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext())
            adapter=homeAdapter
        }
        
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).setVisible()
    }

}