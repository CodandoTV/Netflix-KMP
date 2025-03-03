@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.WKWebsiteDataStore

@Composable
actual fun YoutubePlayerComponentPlatform(videoId: String, modifier: Modifier){
    val webView = remember {
        val embedHTML = videoId.videoIdToEmbedHTML()
        val configuration = WKWebViewConfiguration()
        configuration.websiteDataStore = WKWebsiteDataStore.defaultDataStore()
        configuration.limitsNavigationsToAppBoundDomains = false

        val webView = WKWebView()
        webView.setOpaque(false)
        webView.configuration.websiteDataStore = WKWebsiteDataStore.defaultDataStore()
        webView.configuration.limitsNavigationsToAppBoundDomains = false
        webView.loadHTMLString(
            string = embedHTML,
            baseURL = null
        )
        webView
    }

    UIKitView(
        modifier = modifier,
        factory = {
            webView
        },
        update = { }
    )
}