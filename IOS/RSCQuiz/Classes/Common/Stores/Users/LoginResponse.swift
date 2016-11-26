//
//  LoginResponse.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class LoginResponse {
    
    var token: String?
    var tokenExpires: Int?
    
    required init?(map: Map) { }
}

extension LoginResponse: Mappable {
    
    func mapping(map: Map) {
        token <- map["token"]
        tokenExpires <- map["tokenExpires"]
    }
}
