//
//  UIViewController+Spinner.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import UIKit
import MBProgressHUD

extension UIViewController {
        
    func showSpinner() {
        _ = MBProgressHUD.showAdded(to: self.view, animated: true)
    }
    
    func showSpinnerWithText(text: String) {
        let spinner = MBProgressHUD.showAdded(to: self.view, animated: true)
        spinner.label.text = text
    }
    
    func hideSpinner() {
        _ = MBProgressHUD.hide(for: self.view, animated: true)
    }
}
