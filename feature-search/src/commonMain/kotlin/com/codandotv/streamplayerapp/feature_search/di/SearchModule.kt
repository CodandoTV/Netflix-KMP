package com.codandotv.streamplayerapp.feature_search.di

import com.codandotv.streamplayerapp.feature_search.data.api.SearchStreamService
import com.codandotv.streamplayerapp.feature_search.data.datasource.SearchStreamDataSourceImpl
import com.codandotv.streamplayerapp.feature_search.data.datasource.MostPopularMoviesDataSource
import com.codandotv.streamplayerapp.feature_search.data.datasource.MostPopularMoviesDataSourceImpl
import com.codandotv.streamplayerapp.feature_search.data.repository.MostPopularMoviesRepository
import com.codandotv.streamplayerapp.feature_search.data.repository.MostPopularMoviesRepositoryImpl
import com.codandotv.streamplayerapp.feature_search.data.api.MostPopularMoviesService
import com.codandotv.streamplayerapp.feature_search.data.api.MostPopularMoviesServiceImpl
import com.codandotv.streamplayerapp.feature_search.data.api.SearchStreamServiceImpl
import com.codandotv.streamplayerapp.feature_search.data.datasource.SearchStreamDataSource
import com.codandotv.streamplayerapp.feature_search.data.repository.SearchStreamRepository
import com.codandotv.streamplayerapp.feature_search.data.repository.SearchStreamRepositoryImp
import com.codandotv.streamplayerapp.feature_search.domain.MostPopularMoviesUseCase
import com.codandotv.streamplayerapp.feature_search.domain.MostPopularMoviesUseCaseImpl
import com.codandotv.streamplayerapp.feature_search.domain.SearchUseCase
import com.codandotv.streamplayerapp.feature_search.domain.SearchUseCaseImpl
import com.codandotv.streamplayerapp.feature_search.presentation.screens.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SearchModule {
    val module = module {
        viewModel {
            SearchViewModel(
                searchUseCase = get(),
                mostPopularMoviesUseCase = get()
            )
        }

        factory<SearchStreamService> { SearchStreamServiceImpl(get()) }
        factory<MostPopularMoviesService> { MostPopularMoviesServiceImpl(get()) }

        factory<MostPopularMoviesUseCase> { MostPopularMoviesUseCaseImpl(repository = get()) }
        factory<MostPopularMoviesDataSource> { MostPopularMoviesDataSourceImpl(service = get()) }
        factory<MostPopularMoviesRepository> { MostPopularMoviesRepositoryImpl(dataSource = get()) }

        factory<SearchUseCase> { SearchUseCaseImpl(repository = get()) }
        factory<SearchStreamDataSource> { SearchStreamDataSourceImpl(service = get()) }
        factory<SearchStreamRepository> { SearchStreamRepositoryImp(dataSource = get()) }
    }
}
