package admiral.group.itterminlari.presentation.di.datamodule


import admiral.group.itterminlari.data.local.constant.TerminDatabseConst
import admiral.group.itterminlari.data.local.dao.TerminDao
import admiral.group.itterminlari.data.local.database.TerminDatabase
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
        // Empty implementation, because the schema isn't changing.
        }
    }

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): TerminDatabase {
        return Room.databaseBuilder(
            context, TerminDatabase::class.java,
            TerminDatabseConst.NAME)

            .addMigrations(MIGRATION_1_2)
            .createFromAsset(TerminDatabseConst.NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(terminDatabase: TerminDatabase): TerminDao {
        return terminDatabase.terminDao()
    }
}