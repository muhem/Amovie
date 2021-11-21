package com.erwin2687.amovie.database

import androidx.room.*
import com.erwin2687.amovie.model.Movie


@Dao
interface MovieDB {
    @Query("select * from movie where id=:id")
    fun findById(id: Int): Movie?

    @Query("select * from movie")
    fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("delete from movie where id=:id")
    fun deleteMovieById(id: Int)
}