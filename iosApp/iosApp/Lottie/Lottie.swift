//
//  Lottie.swift
//  iosApp
//
//  Created by Gabriel Bronzatti Moro on 25/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Lottie
import UIKit
import streamplayerapp

class LottieViewProviderImpl : LottieViewProvider {
    func provideLottieView(lottieAnimationJson: String) -> UIView {
        let lottieView = LottieView()
        lottieView.setupLottieAnimationView(
            animationContentJson: lottieAnimationJson
        )
        return lottieView
    }
}

class LottieView : UIView {
    
    private let animationView: LottieAnimationView
    
    override init(frame: CGRect) {
        animationView = LottieAnimationView()

        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented.")
    }
    
    func setupLottieAnimationView(animationContentJson: String) {
        
        animationView.contentMode = .scaleAspectFit
        
        addSubview(animationView)
        
        animationView.translatesAutoresizingMaskIntoConstraints = false

        animationView.leftAnchor.constraint(equalTo: leftAnchor).isActive = true
        animationView.rightAnchor.constraint(equalTo: rightAnchor).isActive = true
        animationView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        animationView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true

        animationView.loopMode = .loop
        animationView.frame = bounds
        
        if let animation = loadLottieAnimation(from: animationContentJson) {
            animationView.animation = animation
            animationView.loopMode = .loop
            animationView.play()
        }
    }
    
    func loadLottieAnimation(from jsonString: String) -> LottieAnimation? {
        guard let data = jsonString.data(using: .utf8) else { return nil }
        return try? JSONDecoder().decode(LottieAnimation.self, from: data)
    }
}
