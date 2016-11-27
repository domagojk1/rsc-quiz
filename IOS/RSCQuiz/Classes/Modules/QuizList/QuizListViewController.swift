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
        // hidesBottomBarWhenPushed = false
    }
    
    func initializeUI() {
        let quiz1 = Quiz()
        quiz1.name = "Kviz 1"
        quiz1.quizDescription = "lalalala"
        quiz1.isEnabled = true
        let quiz2 = Quiz()
        quiz2.name = "Kviz 2"
        quiz2.quizDescription = "lalalala"
        quiz2.isEnabled = true

        let quiz3 = Quiz()
        quiz3.name = "Kviz 3"
        quiz3.quizDescription = "lalalala"
        quiz3.isEnabled = false

        let quiz4 = Quiz()
        quiz4.name = "Kviz 4"
        quiz4.quizDescription = "lalalala"
        quiz4.isEnabled = false

        let quiz5 = Quiz()
        quiz5.name = "Kviz 5"
        quiz5.quizDescription = "lalalala"
        quiz5.isEnabled = false

        quizList.append(quiz1)
        quizList.append(quiz2)
        quizList.append(quiz3)
        quizList.append(quiz4)
        quizList.append(quiz5)
        quizListTableView.reloadData()
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
        if quiz.isEnabled == true {
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
