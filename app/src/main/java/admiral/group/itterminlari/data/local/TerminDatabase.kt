package admiral.group.itterminlari.data.local

import admiral.group.itterminlari.domain.model.Termin
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Termin::class], version = 2)
abstract class TerminDatabase : RoomDatabase(){

    abstract fun terminDao(): TerminDao

    companion object {
        const val DATABASE_NAME: String = "terminlar.db"
    }
}