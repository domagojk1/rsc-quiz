//
//  UserDefaultsHelper.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

class UserDefaultsHelper {
    
    static var userToken: String? {
        get {
            let defaults = UserDefaults.standard
            let trainerName = defaults.string(forKey: "userToken")
            return trainerName
        }
        set {
            let defaults = UserDefaults.standard
            defaults.setValue(newValue, forKey: "userToken")
        }
    }
}
