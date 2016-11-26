//
//  AppDelegate.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import FBSDKCoreKit
import FBSDKLoginKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        FBSDKApplicationDelegate.sharedInstance().application(application, didFinishLaunchingWithOptions: launchOptions)
        
        if let _ = FBSDKAccessToken.current() {
            let storyboard = UIStoryboard(name: "Login", bundle: nil)
            let vc = storyboard.instantiateViewController(withIdentifier: "TabBarViewController")
            window?.rootViewController = vc
        } else {
            let storyboard = UIStoryboard(name: "Login", bundle: nil)
            let vc = storyboard.instantiateInitialViewController()!
            window?.rootViewController = vc
        }
        
        return true
    }
    
    func application(_ application: UIApplication, openURL url: URL, sourceApplication: String?, annotation: AnyObject) -> Bool {
        return FBSDKApplicationDelegate.sharedInstance().application(application, open: url, sourceApplication: sourceApplication, annotation: annotation)
    }
}
