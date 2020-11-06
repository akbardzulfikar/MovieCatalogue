package co.id.akbar.moviecatalogue.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.id.akbar.moviecatalogue.data.source.local.dao.MovieDao
import co.id.akbar.moviecatalogue.data.source.local.dao.TvShowDao
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow

@Database(entities = [MovieEntity::class, TvShow::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private const val DB_NAME = "tmdb"
        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(dbInstance == null) {
                dbInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance as AppDatabase
        }
    }
}