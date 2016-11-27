//
//  ChatMessage.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class ChatMessage {
    
    var username: String?
    var message: String?
    
    required init?(map: Map) { }
}

extension ChatMessage: Mappable {
    
    func mapping(map: Map) {
        username <- map["UserName"]
        message <- map["Message"]
    }
}
