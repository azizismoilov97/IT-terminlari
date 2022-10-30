package admiral.group.itterminlari.presentation.viewmodel

import admiral.group.itterminlari.data.repository.MyRepository
import admiral.group.itterminlari.data.local.entity.Termin
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    var myRepository: MyRepository
) : ViewModel() {

    //// getByOne

    fun getTermin(id:Int)=myRepository.getTermin(id).asLiveData()


    //// getAll

    fun getTermins()=myRepository.getAllTermins().asLiveData()

    //// update
    fun updateTermin(termin: Termin)=
        viewModelScope.launch {
            myRepository.updateTermin(termin)
        }

    //// insert
    fun insertTermin(termin: Termin)=
        viewModelScope.launch {
        myRepository.insertTermin(termin)
    }

}