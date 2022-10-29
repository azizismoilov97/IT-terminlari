package admiral.group.itterminlari.data.local

import admiral.group.itterminlari.domain.model.Termin
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TerminDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(termin: Termin)

    @Query("SELECT * FROM termin")
    fun get(): Flow<List<Termin>>

    @Query("SELECT * FROM termin WHERE id=:id ")
    fun getItemById(id:Int): Flow<Termin>

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun update(termin: Termin):Int

    @Query("UPDATE termin SET word =:word, description =:description, bookmark =:bookmark WHERE id = :id")
    suspend fun update(word: String, description: String, bookmark: Int, id:Int):Int

    @Query("DELETE FROM termin WHERE id = :id")
    suspend fun delete(id: Int)
}