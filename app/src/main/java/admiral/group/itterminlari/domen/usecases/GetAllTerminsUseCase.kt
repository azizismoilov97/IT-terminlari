package admiral.group.itterminlari.domen.usecases

import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.domen.repositories.MyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTerminsUseCase @Inject constructor(
    var myRepository: MyRepository
) {
     operator fun invoke():Flow<List<TerminEntity>> =
         myRepository.getAllTermins()
}