package com.mediapro.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mediapro.localdatasource.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
@TypeConverters(
)
abstract class AppDatabase : RoomDatabase() {
}