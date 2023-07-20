package com.ipons.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ipons.localdatasource.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
@TypeConverters(
)
abstract class AppDatabase : RoomDatabase() {
}