package admiral.group.itterminlari.data.local.dao

import admiral.group.itterminlari.data.local.model.TerminModel
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TerminDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(termin: TerminModel)

    @Query("SELECT * FROM termin")
    fun get(): Flow<List<TerminModel>>

    @Query("SELECT * FROM termin WHERE id=:id ")
    fun getItemById(id:Int): Flow<TerminModel>

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(termin: Termin):Int

    @Query("UPDATE termin SET word =:word, description =:description, bookmark =:bookmark WHERE id = :id")
    suspend fun update(word: String, description: String, bookmark: Int, id:Int):Int

    @Query("DELETE FROM termin WHERE id = :id")
    suspend fun delete(id: Int)
}