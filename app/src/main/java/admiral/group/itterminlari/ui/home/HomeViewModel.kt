package admiral.group.itterminlari.ui.home

import admiral.group.itterminlari.data.repository.MyRepository
import admiral.group.itterminlari.domain.model.Termin
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    var myRepository: MyRepository
) : ViewModel() {

    fun readTermin(id:Int)=myRepository.getTermin(id).asLiveData()

    fun readTermins()=myRepository.getAllTermins().asLiveData()

    fun updateProject(termin: Termin)=
        viewModelScope.launch {
            myRepository.updateTermin(termin)
        }

    fun addTermin(termin: Termin)=viewModelScope.launch {
        myRepository.insertTermin(termin)
    }

}