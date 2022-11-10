package admiral.group.itterminlari.presentation.di.repositorymodule

import admiral.group.itterminlari.domen.repositories.MyRepository
import admiral.group.itterminlari.data.repositories.MyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


/*
 * SOLID. Dependency inversion principle is used.
 *  Framework - Dagger Hilt.
 *  If we use interface, then we cannot constructor-inject it.
 *
 *  Instead, provide Hilt with the binding information
 *  by creating an abstract function annotated with @Binds inside a Hilt module.
 *
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepoModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindPostRepository(myRepositoryImpl: MyRepositoryImpl): MyRepository

}