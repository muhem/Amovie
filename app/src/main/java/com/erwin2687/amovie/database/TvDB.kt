package com.erwin2687.amovie.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erwin2687.amovie.model.TvShow

@Dao
interface TvDB {
    @Query("select * from tv where id=:id")
    fun findById(id: Int): TvShow?

    @Query("select * from tv")
    fun getAllTV(): List<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTV(tv: TvShow)

    @Query("delete from tv where id=:id")
    fun deleteTVById(id: Int)
}