package ir.beigirad.data.store

import ir.beigirad.data.repository.CountryDataStore
import javax.inject.Inject

class CountryDataStoreFactory @Inject constructor(
    private val cacheDataStore: CountryCacheDataStore,
    private val remoteDataStore: CountryRemoteDataStore
) {
    fun getDataStore(isCached: Boolean): CountryDataStore {
        return if (isCached)
            cacheDataStore
        else
            remoteDataStore
    }

    fun getRemoteStore(): CountryDataStore {
        return remoteDataStore
    }

    fun getCacheStore(): CountryDataStore {
        return cacheDataStore
    }
}