package admiral.group.itterminlari.ui.description

import admiral.group.itterminlari.data.repository.MyRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DescriptionViewModel @Inject constructor(
    var myRepository: MyRepository
) : ViewModel() {
    fun readTermin(id:Int)=myRepository.getTermin(id).asLiveData()
}