//
//  UserDefaultsHelper.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

class UserDefaultsHelper {
    
    static var currentUser: User? {
        get {
            let defaults = UserDefaults.standard
            if let data = defaults.object(forKey: "user") {
                return NSKeyedUnarchiver.unarchiveObject(with: data as! Data) as? User
            }
            return nil
        }
        set {
            let data = NSKeyedArchiver.archivedData(withRootObject: newValue)
            let defaults = UserDefaults.standard
            defaults.setValue(data, forKey: "user")
        }
    }
    
    static var userToken: String? {
        get {
            let defaults = UserDefaults.standard
            let token = defaults.string(forKey: "userToken")
            return token
        }
        set {
            let defaults = UserDefaults.standard
            defaults.setValue(newValue, forKey: "userToken")
        }
    }
    
    static var facebookToken: String? {
        get {
            let defaults = UserDefaults.standard
            let token = defaults.string(forKey: "facebookToken")
            return token
        }
        set {
            let defaults = UserDefaults.standard
            defaults.setValue(newValue, forKey: "facebookToken")
        }
    }
    
}
