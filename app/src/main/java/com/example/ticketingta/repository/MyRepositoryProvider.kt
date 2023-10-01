package com.example.ticketingta.repository

import com.example.ticketingta.network.ApiClient

object MyRepositoryProvider {
    private var repository: Repository? = null

    fun provideRepository(apiService: ApiClient): Repository {
        synchronized(this) {
            return repository ?: createRepository(apiService).also {
                repository = it
            }
        }
    }

    private fun createRepository(apiService: ApiClient): Repository {
        return Repository(apiService)
    }
}
