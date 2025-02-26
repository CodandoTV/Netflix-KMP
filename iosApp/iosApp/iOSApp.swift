import SwiftUI
import streamplayerapp

@main
struct iOSApp: App {
    
    init() {
        KoinIosHelper().doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
