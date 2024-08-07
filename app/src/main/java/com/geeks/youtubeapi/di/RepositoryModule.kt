package com.geeks.youtubeapi.di

import com.geeks.youtubeapi.data.repository.YouTubeRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single { YouTubeRepository(get()) }
}