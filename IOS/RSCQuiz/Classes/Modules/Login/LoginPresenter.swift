//
//  LoginPresenter.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

class LoginPresenter {
    
    weak var view: LoginViewDelegate?
    var userStore: UserStore?
    
    init(view: LoginViewDelegate, userStore: UserStore) {
        self.view = view
        self.userStore = userStore
    }
}

extension LoginPresenter: LoginPresenterInterface {
    
    func login(token: String) {
        userStore?.login(token: token, completion: { [weak self] (response) in
            if let _self = self {
                switch response {
                case .success(_):
                    _self.view?.onLoginSuccess()
                case .failure(_):
                    _self.view?.onLoginFailure()
                }
            }
        })
    }
}
