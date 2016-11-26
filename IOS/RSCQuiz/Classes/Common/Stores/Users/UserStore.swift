//
//  UserStore.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class UserStore {
    
    func login(token: String, completion: @escaping (APIResponse<Bool>) -> ()){
        Alamofire
            .request(API.User.loginUrl, method: .post, parameters: ["token": token], encoding: JSONEncoding.default)
            .responseJSON { (dataResponse) in
                if dataResponse.response?.statusCode == 200 {
                    completion(.success(true))
                } else {
                    completion(.failure("Greška"))
                }
            }
    }
}
