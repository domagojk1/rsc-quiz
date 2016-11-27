//
//  QuizListViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import ObjectMapper
import SwiftR

class QuizListViewController: UIViewController {
    
    @IBOutlet weak var quizListTableView: UITableView!
    
    var quizList = [Quiz]()
    
    var chatHub: Hub?
    var connection: SignalR?
    var name: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        quizListTableView.delegate = self
        quizListTableView.dataSource = self
        initializeUI()
        configureSignalR()
        configureConnection()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        fetchData()
    }
    
    func initializeUI() {
        
    }
    
    func fetchData() {
        showSpinner()
        let eventsStore = EventsStore()
        eventsStore.fetchEvents { (response) in
            switch response {
            case .success(let quizList):
                self.quizList = quizList
            case .failure(let error):
                print(error)
            }
            self.quizListTableView.reloadData()
            self.hideSpinner()
        }
    }
    
    // MARK: - Private methods
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "openTeamList" {
            let vc = segue.destination as! TeamListViewController
            let index = sender as! Int
            vc.title = quizList[index].name
        }
    }
    
    private func configureSignalR() {
        SwiftR.useWKWebView = false
        SwiftR.signalRVersion = .v2_2_1
    }
    
    private func configureConnection() {
        connection = SwiftR.connect("http://rsc2016quiz.azurewebsites.net/signalr") { [weak self] (connection) in
            
            connection.headers = ["User-Name": "iOS"]
            
            self?.chatHub = connection.createHubProxy("PostsHub")
            
            self?.chatHub?.on("send", callback: { [weak self] (response) in
                print(response)
                let message = Mapper<ChatMessage>().map(JSON: response?.first as! [String : Any])
                print("\(message!.username!) - \(message!.message!)")
            })
            
            connection.starting = { [weak self] in
                print("Starting connection...")
            }
            
            connection.reconnecting = { print("Reconnectiong...") }
            
            connection.connected = { [weak self] in
                print("Connection ID: \(connection.connectionID!)")
            }
            
            connection.reconnected = { print("Reconnected.") }
            
            connection.disconnected = { print("Disconnected.") }
            
            connection.connectionSlow = { print("Connection slow...") }
            
            connection.error = { error in
                print("Error: \(error)")
                
                if let source = error?["source"] as? String, source == "TimeoutException" {
                    print("Connection timed out. Restarting...")
                    connection.start()
                }
            }
        }
        connection?.start()
    }
}

extension QuizListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let quiz = quizList[indexPath.row]
        if quiz.isOpen == true {
            performSegue(withIdentifier: "openTeamList", sender: indexPath.row)
        } else {
            showPopUpWith(title: "Quiz closed", message: "Quiz is currently closed. Come again later :)")
        }
    }
}

extension QuizListViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuizListTableViewCell") as! QuizListTableViewCell
        cell.quiz = quizList[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return quizList.count
    }
}
