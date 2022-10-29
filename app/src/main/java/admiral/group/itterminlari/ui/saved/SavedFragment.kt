package admiral.group.itterminlari.ui.saved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import admiral.group.itterminlari.R
import admiral.group.itterminlari.databinding.FragmentSavedBinding
import admiral.group.itterminlari.domain.model.Termin
import admiral.group.itterminlari.ui.home.HomeAdapter
import admiral.group.itterminlari.ui.home.HomeFragmentDirections
import admiral.group.itterminlari.ui.home.HomeViewModel
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {

    private val viewBinding:FragmentSavedBinding by viewBinding(FragmentSavedBinding::bind)
    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}
    private val viewModel: SavedViewModel by viewModels()
    private  lateinit var homeAdapter: HomeAdapter
    private lateinit var myList: MutableList<Termin>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding){

            viewModel.readTermins().observe(viewLifecycleOwner){ list ->
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
            viewModel.updateProject(it)
        }, HomeAdapter.OnItemClickListener {
            navController.navigate(SavedFragmentDirections.actionSavedFragment2ToDescriptionFragment2(it))
        })

        myRecyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext())
            adapter=homeAdapter
        }
    }

}