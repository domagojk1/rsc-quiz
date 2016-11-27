//
//  Team.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class Team {
    
    var name: String?
    var password: String?
    var users: [User]?
    
    init() { }
    
    required init?(map: Map) { }
}

extension Team: Mappable {
    
    func mapping(map: Map) {
        name <- map["name"]
        password <- map["password"]
        users <- map["users"]
    }
}
