//
//  EventsResponse.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

enum EventsResponse<T> {
    case success(T)
    case failure(String)
}
