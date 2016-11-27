//
//  TeamsStore.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class TeamsStore {
    
    func createTeam(quizId: Int, password: String, completion: @escaping (APIResponse<CreateTeamResponse>) ->()) {
        let url = URL(string: API.Teams.createTeam)
        Alamofire
            .request(url!,
                     method: .post,
                     parameters: ["name": password, "eventId": quizId],
                     encoding: JSONEncoding.default)
            .responseJSON { (dataResponse) in
                switch dataResponse.result {
                case .success(let value):
                    let response = Mapper<CreateTeamResponse>().map(JSONObject: value)!
                    completion(.success(response))
                case .failure(let error):
                    completion(.failure(error.localizedDescription))
                }
            }
    }
    
    func joinTeam(quizId: Int, teamId: Int, password: String, completion: @escaping (APIResponse<String>) -> ()) {
        let url = URL(string: API.Teams.joinTeam)
        Alamofire
            .request(url!,
                     method: .post,
                     parameters: ["eventId": quizId, "teamId": teamId, "password": password],
                     encoding: JSONEncoding.default,
                     headers: ["Authorization": "Bearer \(UserDefaultsHelper.userToken!)" ])
            .responseJSON { (dataResponse) in
                switch dataResponse.result {
                case .success(_):
                    completion(.success(""))
                case .failure(let error):
                    completion(.failure(error.localizedDescription))
                }
            }
    }
}
