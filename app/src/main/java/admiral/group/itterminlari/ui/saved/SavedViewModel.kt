package admiral.group.itterminlari.ui.saved

import admiral.group.itterminlari.data.repository.MyRepository
import admiral.group.itterminlari.domain.model.Termin
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    var myRepository: MyRepository
) : ViewModel() {

    fun readTermins()=myRepository.getAllTermins().asLiveData()

    fun updateProject(termin: Termin)=
        viewModelScope.launch {
            myRepository.updateTermin(termin)
        }
}