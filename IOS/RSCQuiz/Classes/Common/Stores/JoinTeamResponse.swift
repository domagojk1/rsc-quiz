//
//  JoinTeamResponse.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class JoinTeamResponse {
    
    var message: String?
    
    required init?(map: Map) { }
}

extension JoinTeamResponse: Mappable {
    
    func mapping(map: Map) {
        message <- map["message"]
    }
}
