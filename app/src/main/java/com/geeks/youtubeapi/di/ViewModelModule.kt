package com.geeks.youtubeapi.di

import com.geeks.youtubeapi.ui.fragments.playlist.PlayListsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {
    viewModel {
        PlayListsViewModel(get())
    }
    viewModel {
        PlayListsViewModel(get())
    }
}