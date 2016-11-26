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
    
    @IBOutlet weak var userImageView: UIImageView!
    @IBOutlet weak var userNameLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let user = UserDefaultsHelper.currentUser
        if let data = user?.imageData {
            userImageView.image = UIImage(data: data)
        }
        userNameLabel.text = user?.name!
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
