//
//  QuizListViewController.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit

class QuizListViewController: UIViewController {

    @IBOutlet weak var quizListTableView: UITableView!
    
    var quizList = [Quiz]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        quizListTableView.delegate = self
        quizListTableView.dataSource = self
        initializeUI()
    }
    
    func initializeUI() {
        let quiz1 = Quiz()
        quiz1.name = "Kviz 1"
        quiz1.quizDescription = "lalalala"
        let quiz2 = Quiz()
        quiz2.name = "Kviz 2"
        quiz2.quizDescription = "lalalala"
        let quiz3 = Quiz()
        quiz3.name = "Kviz 3"
        quiz3.quizDescription = "lalalala"
        let quiz4 = Quiz()
        quiz4.name = "Kviz 4"
        quiz1.quizDescription = "lalalala"
        let quiz5 = Quiz()
        quiz5.name = "Kviz 5"
        quiz5.quizDescription = "lalalala"
        quizList.append(quiz1)
        quizList.append(quiz2)
        quizList.append(quiz3)
        quizList.append(quiz4)
        quizList.append(quiz5)
        quizListTableView.reloadData()
    }

}

extension QuizListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
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
