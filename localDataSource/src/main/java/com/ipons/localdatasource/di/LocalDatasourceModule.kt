package com.ipons.localdatasource.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.ipons.localdatasource.AppDatabase
import com.ipons.localdatasource.extensions.getAndroidId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDatasourceModule {

    companion object {
        @VisibleForTesting
        val DATABASE_NAME = "db"
    }

    @Provides
    @Singleton
    fun databaseProvider(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext, AppDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addMigrations()
            .openHelperFactory(getHelperFactory(appContext)).build()

    private fun getHelperFactory(context: Context) = SupportFactory(
        SQLiteDatabase.getBytes(
            context.getAndroidId().toCharArray()
        )
    )
}