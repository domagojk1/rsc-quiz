//
//  User.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import ObjectMapper

class User: NSObject, NSCoding {
    
    fileprivate var firstName: String?
    fileprivate var lastName: String?
    
    var name: String? {
        get {
            return "\(firstName!) \(lastName!)"
        }
    }
    
    var imageUrl: String?
    var imageData: Data?
    
    override init() { }
    
    init(firstName: String, lastName: String, imageUrl: String, imageData: Data) {
        self.firstName = firstName
        self.lastName = lastName
        self.imageUrl = imageUrl
        self.imageData = imageData
    }
    
    required init?(map: Map) { }
    
    required convenience init(coder aDecoder: NSCoder) {
        let firstName = aDecoder.decodeObject(forKey: "firstName") as! String
        let lastName = aDecoder.decodeObject(forKey: "lastName") as! String
        let imageUrl = aDecoder.decodeObject(forKey: "imageUrl") as! String
        let imageData = aDecoder.decodeObject(forKey: "imageData") as! Data
        self.init(firstName: firstName, lastName: lastName, imageUrl: imageUrl, imageData: imageData)
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(firstName, forKey: "firstName")
        aCoder.encode(lastName, forKey: "lastName")
        aCoder.encode(imageUrl, forKey: "imageUrl")
        aCoder.encode(imageData, forKey: "imageData")
    }
}

extension User: Mappable {
    
    func mapping(map: Map) {
        firstName <- map["first_name"]
        lastName <- map["last_name"]
        imageUrl <- map["picture.data.url"]
    }
}
