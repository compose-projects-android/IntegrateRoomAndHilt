package org.compose_projects.IntegrateRoomAndHilt.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.compose_projects.IntegrateRoomAndHilt.data.UsersRepository
import org.compose_projects.IntegrateRoomAndHilt.data.UsersRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsUserRepository(usersRepositoryImp: UsersRepositoryImp) : UsersRepository

}