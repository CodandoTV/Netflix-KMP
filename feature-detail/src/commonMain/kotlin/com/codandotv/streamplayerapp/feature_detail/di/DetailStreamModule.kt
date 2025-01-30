package com.codandotv.streamplayerapp.feature_detail.di

import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamRepository
import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamRepositoryImpl
import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamService
import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamServiceImpl
import com.codandotv.streamplayerapp.feature_detail.domain.DetailStreamUseCase
import com.codandotv.streamplayerapp.feature_detail.domain.DetailStreamUseCaseImpl
import com.codandotv.streamplayerapp.feature_detail.domain.VideoStreamsUseCase
import com.codandotv.streamplayerapp.feature_detail.domain.VideoStreamsUseCaseImpl
import com.codandotv.streamplayerapp.feature_detail.presentation.screens.DetailStreamViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

object DetailStreamModule {
    val module = module {
        viewModel { (id: String) ->
            DetailStreamViewModel(
                detailStreamUseCase = get {
                    parametersOf(id)
                },
                videoStreamsUseCase = get {
                    parametersOf(id)
                },
                dispatcher = Dispatchers.IO
            )
        }
        factory<DetailStreamUseCase> { (id: String) ->
            DetailStreamUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<VideoStreamsUseCase> { (id: String) ->
            VideoStreamsUseCaseImpl(
                detailStreamRepository = get {
                    parametersOf(id)
                }
            )
        }
        factory<DetailStreamRepository> { (id: String) ->
            DetailStreamRepositoryImpl(
                favoriteDao = get(),
                service = get(),
                movieId = id,
            )
        }

        factory<DetailStreamService> {
            DetailStreamServiceImpl(
                client = get()
            )
        }
    }
}