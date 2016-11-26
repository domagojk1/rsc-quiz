//
//  LoginViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import FBSDKCoreKit
import FBSDKLoginKit

class LoginViewController: UIViewController {
    
    @IBOutlet weak var facebookLoginButton: UIButton!
    @IBOutlet weak var googleLoginButton: UIButton!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    var presenter: LoginPresenterInterface?
    
    var facebookButton: FBSDKLoginButton = {
        let button = FBSDKLoginButton()
        button.readPermissions = ["public_profile"]
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter = LoginPresenter(view: self, userStore: UserStore())
        initializeUI()
    }
    
    // MARK: - Public methods
    
    public func didTapFacebookLoginButton() {
        facebookButton.sendActions(for: .touchUpInside)
        activityIndicator.startAnimating()
        activityIndicator.isHidden = false
    }
    
    public func didTapGoogleLoginButton() {
        
    }
    
    // MARK: - Private methods
    
    private func initializeUI() {
        activityIndicator.isHidden = true
        facebookButton.delegate = self
        facebookLoginButton.addTarget(self, action: #selector(didTapFacebookLoginButton), for: .touchUpInside)
        googleLoginButton.addTarget(self, action: #selector(didTapGoogleLoginButton), for: .touchUpInside)
    }
}

extension LoginViewController: LoginViewDelegate {
    
    func onLoginSuccess() {
        print("Login successful.")
        presenter?.downloadUserData()
    }
    
    func onLoginFailure() {
        print("Login failure.")
    }
    
    func onDownloadUserData() {
        activityIndicator.stopAnimating()
        activityIndicator.isHidden = true
        performSegue(withIdentifier: "openApplication", sender: self)
    }
}

extension LoginViewController: FBSDKLoginButtonDelegate {
    
    func loginButton(_ loginButton: FBSDKLoginButton!, didCompleteWith result: FBSDKLoginManagerLoginResult!, error: Error!) {
        if let result = result {
            if let token = result.token {
                presenter?.login(token: token.tokenString!)
            } else {
                onLoginFailure()
            }
        } else {
            onLoginFailure()
        }
    }
    
    func loginButtonDidLogOut(_ loginButton: FBSDKLoginButton!) {
        print("Logout successful.")
    }
}
