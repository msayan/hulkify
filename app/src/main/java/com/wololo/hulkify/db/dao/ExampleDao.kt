package com.wololo.hulkify.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.wololo.hulkify.db.entities.Example

@Dao
interface ExampleDao {

    @Query("SELECT * FROM Example")
    fun getExamples(): LiveData<List<Example>>

    @Query("SELECT * FROM Example where id = :exampleId")
    fun getExample(exampleId: Long): Example

    @Insert(onConflict = REPLACE)
    fun insertExample(example: Example)

    @Update
    fun updateExample(example: Example)

    @Delete
    fun deleteExample(example: Example)

    @Query("Select count(*) from Example")
    fun getCount(): Int

}