//
//  EventsStore.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

class EventsStore {
    
    func fetchEvents(completion: @escaping (EventsResponse<[Quiz]>) -> ()) {
        let url = URL(string: API.Events.eventsUrl)!
        Alamofire
            .request(url,
                     method: .get,
                     encoding: JSONEncoding.default)
            .responseJSON { (dataResponse) in
                switch dataResponse.result {
                case .success(let value):
                    let quizList = Mapper<Quiz>().mapArray(JSONObject: value)!
                    completion(.success(quizList))
                case .failure(let error):
                    completion(.failure(error.localizedDescription))
                }
            }
    }
}
