package org.compose_projects.IntegrateRoomAndHilt.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.compose_projects.IntegrateRoomAndHilt.data.local.CONSTANTS
import org.compose_projects.IntegrateRoomAndHilt.data.local.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            CONSTANTS.NAMEDATABASE
        ).build()


    @Provides
    fun provideUsersDao(appDatabase: AppDatabase) =
        appDatabase.usersDao()
}