//
//  API.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import Foundation

struct API {
    
    // MARK: - Private properties
    
    private static var baseUrl: String {
        get {
            return "http://rsc2016quiz.azurewebsites.net"
        }
    }
    
    // MARK: - User
    
    struct User {
        
        static var loginUrl: String {
            get {
                return baseUrl.appending("/api/Users/login")
            }
        }
    }
    
    struct Events {
        
        static var eventsUrl: String {
            get {
                return baseUrl.appending("/api/Events")
            }
        }
    }
    
    struct Teams {
        
        static var createTeam: String {
            get {
                return baseUrl.appending("/api/Teams/Create")
            }
        }
        
        static var joinTeam: String {
            get {
                return baseUrl.appending("/api/Teams/join")
            }
        }
    }
}
