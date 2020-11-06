package co.id.akbar.moviecatalogue.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

@Dao
interface TvShowDao {
    @get:Query("SELECT * FROM TvShow")
    val all: LiveData<List<TvShow>>

    @Query("SELECT * FROM TvShow")
    fun allAsPaged(): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM TvShow WHERE id = :id")
    fun getById(id: Int?): TvShow?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShow: TvShow)

    @Delete
    fun delete(tvShow: TvShow)
}