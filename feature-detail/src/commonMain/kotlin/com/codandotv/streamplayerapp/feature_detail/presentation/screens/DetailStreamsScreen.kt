package com.codandotv.streamplayerapp.feature_detail.presentation.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codandotv.streamplayerapp.core_shared_ui.widget.SharingStreamCustomView
import com.codandotv.streamplayerapp.feature.detail.R
import com.codandotv.streamplayerapp.feature_detail.domain.DetailStream
import com.codandotv.streamplayerapp.feature_detail.presentation.widget.DetailStreamActionOption
import com.codandotv.streamplayerapp.feature_detail.presentation.widget.DetailStreamButtonAction
import com.codandotv.streamplayerapp.feature_detail.presentation.widget.DetailStreamImagePreview
import com.codandotv.streamplayerapp.feature_detail.presentation.widget.DetailStreamRowHeader
import com.codandotv.streamplayerapp.feature_detail.presentation.widget.DetailStreamToolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailStreamScreen(
    viewModel: DetailStreamViewModel = koinViewModel(),
    navController: NavController,
    disposable: () -> Unit = {},
    onNavigateSearchScreen: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.loadDetail()
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            disposable.invoke()
        }
    }

    when (uiState) {
        is DetailStreamsUIState.DetailStreamsLoadedUIState -> {
            SetupDetailScreen(
                onToggleToMyList = { detailStream -> viewModel.toggleItemInFavorites(detailStream) },
                uiState = uiState as DetailStreamsUIState.DetailStreamsLoadedUIState,
                navController = navController,
                onNavigateSearchScreen = onNavigateSearchScreen
            )
        }

        else -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("LongMethod")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun SetupDetailScreen(
    onToggleToMyList: (DetailStream) -> Unit,
    uiState: DetailStreamsUIState.DetailStreamsLoadedUIState,
    navController: NavController,
    onNavigateSearchScreen: () -> Unit = {},
) {
    val showDialog = remember { mutableStateOf(false) }

    var showPlayer by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            DetailStreamToolbar(
                navController = navController,
                onNavigateSearchScreen = onNavigateSearchScreen
            )
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                DetailStreamImagePreview(
                    uiState = uiState,
                    onPlayEvent = {
                        showPlayer = true
                    },
                    showPlayer = showPlayer
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                ) {
                    DetailStreamRowHeader()
                    Text(
                        text = uiState.detailStream.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold, fontSize = 28.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = uiState.detailStream.releaseYear,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailStreamButtonAction(
                        buttonsColors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        imageVector = Icons.Filled.PlayArrow,
                        imageVectorColor = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(R.string.detail_watch_primary_button),
                        textColor = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    DetailStreamButtonAction(
                        buttonsColors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        imageVector = Icons.Filled.Add,
                        imageVectorColor = MaterialTheme.colorScheme.onSurface,
                        text = stringResource(id = R.string.detail_default_text_secondary_button),
                        textColor = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = uiState.detailStream.overview,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            lineHeight = 1.25.em
                        ),
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DetailStreamActionOption(
                        uiState.detailStream,
                        onToggleToMyList,
                        { showDialog.value = true })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if (showDialog.value) {
                SharingStreamCustomView(
                    contentTitle = uiState.detailStream.title,
                    contentUrl = uiState.detailStream.url,
                    setShowDialog = {
                        showDialog.value = it
                    })
            }
            BackHandler {
                if (showDialog.value) {
                    showDialog.value = false
                } else {
                    navController.navigateUp()
                }
            }
        })
}