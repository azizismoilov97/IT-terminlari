package admiral.group.itterminlari.data.local.database

import admiral.group.itterminlari.data.local.constant.TerminDatabseConst
import admiral.group.itterminlari.data.local.dao.TerminDao
import admiral.group.itterminlari.data.local.entity.Termin
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Termin::class], version = TerminDatabseConst.VERSION)
abstract class TerminDatabase : RoomDatabase(){

    abstract fun terminDao(): TerminDao

    companion object {
        const val DATABASE_NAME: String = TerminDatabseConst.NAME
    }
}