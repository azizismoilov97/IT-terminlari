package admiral.group.itterminlari.data.repository

import admiral.group.itterminlari.domain.model.Termin
import kotlinx.coroutines.flow.Flow

interface MyRepository {

    fun getAllTermins(): Flow<List<Termin>>
    fun getTermin(id:Int): Flow<Termin>
    suspend fun updateTermin(termin: Termin):Int
    suspend fun deleteTermin(id: Int)
    suspend fun insertTermin(termin: Termin)

}