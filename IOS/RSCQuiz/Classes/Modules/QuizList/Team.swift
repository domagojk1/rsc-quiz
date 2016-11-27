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
    
    var id: Int?
    var name: String?
    var password: String?
    var users = [User]()
    
    var eventId: Int?
    var score: Int?
    var isWinner: Bool?
    
    init() { }
    
    required init?(map: Map) { }
}

extension Team: Mappable {
    
    func mapping(map: Map) {
        id <- map["id"]
        name <- map["name"]
        password <- map["password"]
        // users <- map["users"]
        // score <- map["score"]
        // isWinner <- map["isWinner"]
        // eventId <- map["eventId"]
    }
}
