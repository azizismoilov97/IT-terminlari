package admiral.group.itterminlari.domen.usecases

import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.domen.repositories.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTerminUseCase @Inject constructor(
    var myRepository: MyRepository
) {
   operator fun invoke(id:Int): Flow<TerminEntity> = myRepository.getTermin(id)
}