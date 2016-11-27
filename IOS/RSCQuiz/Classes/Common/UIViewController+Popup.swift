//
//  UIViewController+Popup.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    
    func showPopUpWithActions(title: String, user: User, completion: @escaping (Team!) -> ()) {
        let alertController = UIAlertController(title: title, message: "Enter team name:", preferredStyle: .alert)
        
        let saveAction = UIAlertAction(title: "Add", style: .default) { (alertAction) in
            let textField1 = alertController.textFields![0] as UITextField
            
            let textValue1 = textField1.text!
            if textValue1.isEmpty || textValue1.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
                completion(nil)
                return
            }
        
            let team = Team()
            team.name = textField1.text!
            var users = [User]()
            users.append(user)
            team.users = users
            completion(team)
        }
        
        let cancelAction = UIAlertAction(title: "Cancel", style: .default, handler: nil)
        
        alertController.addTextField { (textField : UITextField!) -> Void in
            textField.placeholder = "Enter team name"
        }
        
        alertController.addAction(saveAction)
        alertController.addAction(cancelAction)
        
        self.present(alertController, animated: true, completion: nil)
    }
    
    func showPopUpWithAction(goIn: Bool, title: String, password: String, completion: @escaping (Bool) -> ()) {
        let alertController = UIAlertController(title: title, message: "Enter team password:", preferredStyle: .alert)
    
        let saveAction = UIAlertAction(title: "Join", style: .default) { (alertAction) in
            let textField1 = alertController.textFields![0] as UITextField
            
            let textValue1 = textField1.text!
            if textValue1.isEmpty || textValue1.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
                completion(false)
                return
            }
            if textValue1 != password {
                completion(false)
                return
            }
            completion(true)
        }
        
        let cancelAction = UIAlertAction(title: "Cancel", style: .default, handler: nil)
        alertController.addTextField { (textField : UITextField!) -> Void in
            textField.placeholder = "Enter team password"
        }
        
        alertController.addAction(saveAction)
        alertController.addAction(cancelAction)
        self.present(alertController, animated: true, completion: nil)
    }
    
    func showPopUpWith(goIn: Bool, title: String, message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let OKAction = UIAlertAction(title: "OK", style: .default) { (alertAction) in
            if goIn {
                self.performSegue(withIdentifier: "openQuiz", sender: nil)
            }
        }
        alertController.addAction(OKAction)
        
        self.present(alertController, animated: true, completion: nil)
    }
}
