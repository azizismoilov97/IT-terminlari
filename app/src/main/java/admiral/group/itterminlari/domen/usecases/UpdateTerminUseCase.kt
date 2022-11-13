package admiral.group.itterminlari.domen.usecases

import admiral.group.itterminlari.data.mapper.toModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.domen.repositories.MyRepository
import javax.inject.Inject

class UpdateTerminUseCase @Inject constructor(
    var myRepository: MyRepository
) {

    suspend operator fun invoke(terminEntity: TerminEntity)=myRepository.updateTermin(
        terminEntity
    )


}