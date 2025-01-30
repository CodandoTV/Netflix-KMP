package com.codandotv.streamplayerapp.profile.di

import com.codandotv.streamplayerapp.profile.data.ProfilePickerStreamRepository
import com.codandotv.streamplayerapp.profile.data.ProfilePickerStreamRepositoryImpl
import com.codandotv.streamplayerapp.profile.data.ProfilePickerStreamService
import com.codandotv.streamplayerapp.profile.data.ProfilePickerStreamServiceImpl
import com.codandotv.streamplayerapp.profile.domain.ProfilePickerStreamUseCase
import com.codandotv.streamplayerapp.profile.domain.ProfilePickerStreamUseCaseImpl
import com.codandotv.streamplayerapp.profile.presentation.screens.ProfilePickerStreamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ProfilePickerStreamModule {
    val module = module {
        viewModel {
            ProfilePickerStreamViewModel(
                useCase = get()
            )
        }

        factory<ProfilePickerStreamUseCase> {
            ProfilePickerStreamUseCaseImpl(
                profilePickerStreamRepository = get()
            )
        }

        factory<ProfilePickerStreamRepository> {
            ProfilePickerStreamRepositoryImpl(
                service = get()
            )
        }

        factory<ProfilePickerStreamService> {
            ProfilePickerStreamServiceImpl(
                client = get()
            )
        }
    }
}