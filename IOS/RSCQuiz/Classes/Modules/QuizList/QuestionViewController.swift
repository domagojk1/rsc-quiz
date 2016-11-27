//
//  QuestionViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import SwiftR
import ObjectMapper

class QuestionViewController: UIViewController {

    @IBOutlet weak var questionTime: UILabel!
    @IBOutlet weak var questionTextLabel: UILabel!
    @IBOutlet weak var questionTableView: UITableView!
    @IBOutlet weak var answerButton: UIButton!
    
    var number: Int?
    var question: Question?
    
    var chatHub: Hub?
    var connection: SignalR?
    var name: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        questionTableView.delegate = self
        questionTableView.dataSource = self
        navigationItem.hidesBackButton = true
        answerButton.addTarget(self, action: #selector(didTapAnswer), for: .touchUpInside)

        var answers = [Answer]()
        let answer1 = Answer()
        answer1.checked = false
        answer1.text = "Varaždin"
        let answer2 = Answer()
        answer2.checked = false
        answer2.text = "Zagreb"
        let answer3 = Answer()
        answer3.checked = false
        answer3.text = "Osijek"
        let answer4 = Answer()
        answer4.checked = false
        answer4.text = "Rijeka"
        answers.append(answer1)
        answers.append(answer2)
        answers.append(answer3)
        answers.append(answer4)
        
        question = Question()
        question?.question = "Which city is capital of Croatia?"
        question?.answers = answers
        
        questionTableView.reloadData()
        // Do any additional setup after loading the view.
        initialize()
    }
    
    func didTapAnswer() {
        // TODO: Send answer
        
        let storyboard = UIStoryboard(name: "QuizList", bundle: nil)
        number = number! + 1
        if number! > 3 {
            let vc = storyboard.instantiateViewController(withIdentifier: "QuizResultViewController") as! QuizResultViewController
            show(vc, sender: nil)

        } else {
            let vc = storyboard.instantiateViewController(withIdentifier: "QuestionViewController") as! QuestionViewController
            vc.number = number
            show(vc, sender: nil)
        }
    }
    
    func initialize() {
        configureSignalR()
        configureConnection()
    }
    
    private func configureSignalR() {
        SwiftR.useWKWebView = false
        SwiftR.signalRVersion = .v2_2_1
    }
    
    private func configureConnection() {
        connection = SwiftR.connect("http://rsc2016quiz.azurewebsites.net/signalr") { [weak self] (connection) in
            
            connection.headers = ["User-Name": UserDefaultsHelper.currentUser!.name!]
            
            self?.chatHub = connection.createHubProxy("PostsHub")
            
            self?.chatHub?.on("sendNextQuestion", callback: { (response) in
                print(response)
                let message = Mapper<ChatMessage>().map(JSON: response?.first as! [String : Any])
                print("\(message!.username!) - \(message!.message!)")
            })
            
            self?.chatHub?.on("SendNextQuestion", callback: { (response) in
                print(response ?? "")
                let message = Mapper<ChatMessage>().map(JSON: response?.first as! [String : Any])
                print("\(message!.username!) - \(message!.message!)")
            })
            
            connection.starting = { print("Starting connection...") }
            
            connection.reconnecting = { print("Reconnectiong...") }
            
            connection.connected = { print("Connection ID: \(connection.connectionID!)") }
            
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

extension QuestionViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

extension QuestionViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return question!.answers!.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuestionTableViewCell") as! QuestionTableViewCell
        cell.answer = question?.answers?[indexPath.row]
        return cell
    }
}
