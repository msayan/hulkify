package com.wololo.hulkify.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.wololo.hulkify.db.dao.ExampleDao
import com.wololo.hulkify.db.entities.Example

@Database(entities = arrayOf(Example::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}