//
//  SettingsViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import FBSDKLoginKit

class SettingsViewController: UIViewController {

    @IBOutlet weak var logoutButton: UIBarButtonItem!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    @IBAction func didTapLogoutButton(_ sender: UIBarButtonItem) {
        logout()
    }
    
    private func logout() {
        print("Logout")
        FBSDKLoginManager().logOut()
        let storyboard = UIStoryboard(name: "Login", bundle: nil)
        let viewController = storyboard.instantiateInitialViewController()!
        UIApplication.shared.keyWindow?.rootViewController = viewController
    }
}


extension SettingsViewController: SettingsViewDelegate {
    
}
