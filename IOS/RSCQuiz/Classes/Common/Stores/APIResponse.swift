//
//  APIResponse.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

enum APIResponse<T> {
    case success(T)
    case failure(String)
}
