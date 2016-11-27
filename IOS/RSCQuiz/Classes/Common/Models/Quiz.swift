//
//  Quiz.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class Quiz {
    
    var id: Int?
    var name: String?
    var place: String?
    var dateTime: String?
    var quizDescription: String?
    var isOpen: Bool?
    var teams: [Team]?
    
    required convenience init() {
        self.init()
    }
    
    required init?(map: Map) { }
}

extension Quiz: Mappable {
    
    func mapping(map: Map) {
        id <- map["id"]
        name <- map["name"]
        place <- map["place"]
        dateTime <- map["dateTime"]
        quizDescription <- map["description"]
        isOpen <- map["isOpen"]
        // teams <- map["teams"]
    }
}
