package admiral.group.itterminlari.data.local.model

import admiral.group.itterminlari.data.local.constant.TerminDatabseConst
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TerminDatabseConst.TABLE_NOTE)
data class TerminModel(

    @ColumnInfo(name = "word")
    var word:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "bookmark")
    var bookmark:Int=0,

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id:Int
)
