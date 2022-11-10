package admiral.group.itterminlari.presentation.viewmodel

import admiral.group.itterminlari.domen.repositories.MyRepository
import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.domen.mapper.toEntity
import admiral.group.itterminlari.domen.mapper.toModel
import admiral.group.itterminlari.domen.usecases.GetAllTerminsUseCase
import admiral.group.itterminlari.domen.usecases.GetTerminUseCase
import admiral.group.itterminlari.domen.usecases.InsertTerminUseCase
import admiral.group.itterminlari.domen.usecases.UpdateTerminUseCase
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var getAllTerminsUseCase: GetAllTerminsUseCase,
    var getTerminUseCase:GetTerminUseCase,
    var updateTerminUseCase: UpdateTerminUseCase,
    var insertTerminUseCase: InsertTerminUseCase,
    var myRepository: MyRepository
) : ViewModel() {

    private var _listTermins = MutableLiveData<List<TerminEntity>>()


    val listTermins:LiveData<List<TerminEntity>>
      get() = _listTermins

    private var _termin = MutableLiveData<TerminEntity>()


    val termin:LiveData<TerminEntity>
        get() = _termin


    //// getByOne
    fun getTermin(id:Int)=
        viewModelScope.launch {
            getTerminUseCase(id).collect{
                _termin.value=it
            }
        }


    //// getAll
    fun getTermins()=
        viewModelScope.launch {
        getAllTerminsUseCase().collect{
          _listTermins.value=it

        }
        }


    //// update
    fun updateTermin(terminEntity: TerminEntity)=
        viewModelScope.launch() {
            updateTerminUseCase(terminEntity)
        }


    //// insert
    fun insertTermin(terminEntity: TerminEntity)=
        viewModelScope.launch {
        insertTerminUseCase(terminEntity)
    }

}

