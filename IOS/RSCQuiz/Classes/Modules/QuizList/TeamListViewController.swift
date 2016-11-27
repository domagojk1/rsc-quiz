//
//  TeamListViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit

class TeamListViewController: UIViewController {    
    
    @IBOutlet weak var teamsTableView: UITableView!
    
    var teamList = [Team]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        teamsTableView.delegate = self
        teamsTableView.dataSource = self
        
        let team = Team()
        team.name = "Team 1"
        team.users = [User]()
        team.password = "1234"
        teamList.append(team)
        let team2 = Team()
        team2.name = "Team 2"
        team2.password = "5678"
        team2.users = [User]()
        teamList.append(team2)
        
        teamsTableView.reloadData()
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
                self.teamList.append(team)
                self.teamsTableView.reloadData()
                self.performSegue(withIdentifier: "openQuiz", sender: nil)
            } else {
                self.showPopUpWith(title: "Error with adding", message: "You must enter team name and password.")
            }
        }
    }
    
    fileprivate func addMemberToTeam(team: Team, row: Int) {
        showPopUpWithAction(title: "Join team", password: team.password!) { (result) in
            if result == true {
                self.teamList[row].users!.append(UserDefaultsHelper.currentUser!)
                self.teamsTableView.reloadData()
                self.performSegue(withIdentifier: "openQuiz", sender: nil)
            } else {
                self.showPopUpWith(title: "Error", message: "You must provide right password for joining a team.")
            }
        }
    }
}

extension TeamListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let team = teamList[indexPath.row]
        addMemberToTeam(team: team, row: indexPath.row)
    }
}

extension TeamListViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return teamList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TeamTableViewCell") as! TeamTableViewCell
        cell.team = teamList[indexPath.row]
        return cell
    }
}
