package co.id.akbar.moviecatalogue.di

import android.app.Application
import co.id.akbar.moviecatalogue.data.source.CatalogueRepository
import co.id.akbar.moviecatalogue.data.source.local.LocalRepository
import co.id.akbar.moviecatalogue.data.source.remote.RemoteRepository


class Injection {
    companion object {
        fun provideRepository(application: Application): CatalogueRepository {
            val remoteRepository = RemoteRepository.getInstance()
            val localRepository = LocalRepository.getInstance(application)
            return CatalogueRepository.getInstance(remoteRepository, localRepository)
        }
    }
}