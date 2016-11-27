//
//  TeamListViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import ObjectMapper
import SwiftR

class TeamListViewController: UIViewController {    
    
    @IBOutlet weak var teamsTableView: UITableView!
    
    var quiz: Quiz?
    var teamList: [Team]?
    
    var chatHub: Hub?
    var connection: SignalR?
    var name: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        teamsTableView.delegate = self
        teamsTableView.dataSource = self
        teamsTableView.reloadData()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        initialize()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        if let conn = connection {
            conn.stop()
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "openQuiz" {
            let vc = segue.destination as! QuestionViewController
            vc.number = 1
        }
    }
    
    @IBAction func didTapAddNewTeamButton(_ sender: UIBarButtonItem) {
        showPopUpWithActions(title: "Add team", user: UserDefaultsHelper.currentUser!) { (team) in
            if let team = team {
                
                let teamsStore = TeamsStore()
                teamsStore.createTeam(quizId: (self.quiz?.id)!, password: team.name!, completion: { (response) in
                    switch response {
                    case .success(let value):
                        team.eventId = value.eventId
                        team.password = value.password
                        self.teamList?.append(team)
                        self.teamsTableView.reloadData()
                        self.showPopUpWith(goIn: true, title: "Team password", message: team.password!)
                    case .failure(let error):
                        print(error)
                        self.showPopUpWith(goIn: false, title: "Error", message: "Error has occured with creating team.")
                    }
                })
            } else {
                self.showPopUpWith(goIn: false, title: "Error with adding", message: "You must enter team name and password.")
            }
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
            
            self?.chatHub?.on("sendTeamList", callback: { (response) in
                print(response)
                let message = Mapper<ChatMessage>().map(JSON: response?.first as! [String : Any])
                print("\(message!.username!) - \(message!.message!)")
            })
            
            self?.chatHub?.on("SendTeamList", callback: { (response) in
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
    
    fileprivate func addMemberToTeam(team: Team, row: Int) {
        showPopUpWithAction(goIn: false, title: "Join team", password: team.password!) { (result) in
            if result == true {
                let teamsStore = TeamsStore()
                teamsStore.joinTeam(quizId: (self.quiz?.id)!, teamId: team.id!, password: team.password!, completion: { (response) in
                    switch response {
                    case .success(_):
                        self.teamList?[row].users.append(UserDefaultsHelper.currentUser!)
                        self.teamsTableView.reloadData()
                        self.performSegue(withIdentifier: "openQuiz", sender: nil)
                    case .failure(_):
                        self.showPopUpWith(goIn: false, title: "Error", message: "You must provide right password for joining a team.")
                    }
                })
            } else {
                self.showPopUpWith(goIn: false, title: "Error", message: "You must provide right password for joining a team.")
            }
        }
    }
}

extension TeamListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let team = teamList?[indexPath.row]
        addMemberToTeam(team: team!, row: indexPath.row)
    }
}

extension TeamListViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (teamList?.count)!
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TeamTableViewCell") as! TeamTableViewCell
        cell.team = teamList?[indexPath.row]
        return cell
    }
}
