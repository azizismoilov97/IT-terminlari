package admiral.group.itterminlari.data.repository

import admiral.group.itterminlari.data.local.dao.TerminDao
import admiral.group.itterminlari.data.local.entity.Termin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MyRepositoryImpl @Inject constructor(
    private val terminDao: TerminDao
): MyRepository {


    override fun getAllTermins(): Flow<List<Termin>> = terminDao.get()

    override fun getTermin(id: Int): Flow<Termin> = terminDao.getItemById(id)

    override suspend fun updateTermin(termin: Termin): Int = terminDao.update(termin.word, termin.description, termin.bookmark, termin.id)

    override suspend fun deleteTermin(id: Int) = terminDao.delete(id)

    override suspend fun insertTermin(termin: Termin) = terminDao.insert(termin)


}