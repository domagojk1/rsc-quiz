//
//  LoginInterfaces.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

protocol LoginViewDelegate: class {
    
    func onLoginSuccess()
    func onLoginFailure()
}

protocol LoginPresenterInterface: class {
    
    func login(token: String)
}

