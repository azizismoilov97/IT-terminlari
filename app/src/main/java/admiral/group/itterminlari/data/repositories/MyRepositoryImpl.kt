package admiral.group.itterminlari.data.repositories

import admiral.group.itterminlari.data.local.dao.TerminDao
import admiral.group.itterminlari.data.local.model.TerminModel
import admiral.group.itterminlari.domen.entities.TerminEntity
import admiral.group.itterminlari.domen.mapper.toEntity
import admiral.group.itterminlari.domen.mapper.toModel
import admiral.group.itterminlari.domen.repositories.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MyRepositoryImpl @Inject constructor(
    private val terminDao: TerminDao
): MyRepository {

    override fun getAllTermins(): Flow<List<TerminEntity>> = terminDao.get().map { models ->
        models.map { it.toEntity() }
    }

    override fun getTermin(id: Int): Flow<TerminEntity> = terminDao.getItemById(id).map {
        it.toEntity()
    }

    override suspend fun updateTermin(termin: TerminEntity): Int =
        withContext(Dispatchers.IO) {
            terminDao.update(termin.word, termin.description, termin.bookmark, termin.id)
        }

    override suspend fun deleteTermin(id: Int) =
        withContext(Dispatchers.IO){
            terminDao.delete(id)
        }

    override suspend fun insertTermin(termin: TerminEntity) =
        withContext(Dispatchers.IO) {
            terminDao.insert(termin.toModel())
        }

}