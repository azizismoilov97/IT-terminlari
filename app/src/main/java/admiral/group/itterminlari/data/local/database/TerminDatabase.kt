package admiral.group.itterminlari.data.local.database

import admiral.group.itterminlari.data.local.constant.TerminDatabseConst
import admiral.group.itterminlari.data.local.dao.TerminDao
import admiral.group.itterminlari.data.local.model.TerminModel
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TerminModel::class], version = TerminDatabseConst.VERSION)
abstract class TerminDatabase : RoomDatabase(){
    abstract fun terminDao(): TerminDao
}