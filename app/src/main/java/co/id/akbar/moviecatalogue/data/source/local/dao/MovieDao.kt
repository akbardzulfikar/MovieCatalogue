package co.id.akbar.moviecatalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @get:Query("SELECT * FROM Movie")
    val all: LiveData<List<MovieEntity>>

    @Query("SELECT * FROM Movie")
    fun allAsPaged(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id: Int?): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity): Long

    @Delete
    fun delete(movie: MovieEntity)

    @Query("DELETE FROM Movie WHERE id = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(movie: MovieEntity): Int
}