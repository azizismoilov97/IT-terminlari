package admiral.group.itterminlari.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "termin")
data class Termin(

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
