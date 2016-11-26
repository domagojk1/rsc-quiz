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
    
    func login(token: String, completion: @escaping (APIResponse<LoginResponse>) -> ()){
        Alamofire
            .request(API.User.loginUrl,
                     method: .post,
                     parameters: ["token": token],
                     encoding: JSONEncoding.default)
            .responseJSON { (dataResponse) in
                switch dataResponse.result {
                case .success(let value):
                    let loginResponse = Mapper<LoginResponse>().map(JSONObject: value)
                    if let response = loginResponse {
                        completion(.success(response))
                    } else {
                        completion(.failure("Greška"))
                    }
                case .failure(let error):
                    completion(.failure(error.localizedDescription))
                }
            }
    }
}
