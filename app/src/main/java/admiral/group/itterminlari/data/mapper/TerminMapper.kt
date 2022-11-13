package admiral.group.itterminlari.data.mapper

import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity


fun TerminModel.toEntity(): TerminEntity {
    return TerminEntity(
        word = word,
        description = description,
        bookmark = bookmark,
        id = id
    )
}


fun TerminEntity.toModel(): TerminModel {
    return TerminModel(
        word = word,
        description = description,
        bookmark = bookmark,
        id = id
    )
}