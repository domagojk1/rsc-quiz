//
//  QuizListTableViewCell.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit

class QuizListTableViewCell: UITableViewCell {

    @IBOutlet weak var checkView: UIView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    var quiz: Quiz? {
        willSet {
            nameLabel.text = newValue?.name
            descriptionLabel.text = newValue?.quizDescription
        }
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}
