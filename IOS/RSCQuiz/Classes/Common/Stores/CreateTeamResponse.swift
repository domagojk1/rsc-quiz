//
//  CreateTeamResponse.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class CreateTeamResponse {
    
    var password: String?
    var teamId: Int?
    var eventId: Int?
    
    required init?(map: Map) { }
}

extension CreateTeamResponse: Mappable {
    
    func mapping(map: Map) {
        password <- map["password"]
        teamId <- map["teamId"]
        eventId <- map["eventId"]
    }
}
