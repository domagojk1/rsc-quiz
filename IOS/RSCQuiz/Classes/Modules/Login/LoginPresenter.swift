//
//  LoginPresenter.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import FBSDKCoreKit
import FBSDKLoginKit
import ObjectMapper

class LoginPresenter {
    
    weak var view: LoginViewDelegate?
    var userStore: UserStore?
    
    init(view: LoginViewDelegate, userStore: UserStore) {
        self.view = view
        self.userStore = userStore
    }
    
    fileprivate func saveLognCredentials(token: String, tokenExpires: Int) {
        UserDefaultsHelper.userToken = token
    }
}

extension LoginPresenter: LoginPresenterInterface {
    
    func login(token: String) {
        UserDefaultsHelper.facebookToken = token
        userStore?.login(token: token, completion: { [weak self] (response) in
            if let _self = self {
                switch response {
                case .success(let response):
                    _self.view?.onLoginSuccess()
                    _self.saveLognCredentials(token: response.token!, tokenExpires: response.tokenExpires!)
                case .failure(_):
                    _self.view?.onLoginFailure()
                }
            }
        })
    }
    
    func downloadUserData() {
        let parameters = ["fields": "email, first_name, last_name, picture.type(large)"]
        FBSDKGraphRequest(graphPath: "me", parameters: parameters).start { (connection, result, error) in
            if error != nil {
                self.view?.onDownloadUserData()
                return
            }
            let user = Mapper<User>().map(JSONObject: result)
            
            print(user!.imageUrl!)
            self.userStore?.downloadFBPicture(url: user!.imageUrl!, completion: { (response) in
                switch response {
                case .success(let value):
                    if let data = value {
                        user?.imageData = data
                    }
                case .failure(_):
                    break
                }
                UserDefaultsHelper.currentUser = user
                self.view?.onDownloadUserData()
            })
        }
    }
}
