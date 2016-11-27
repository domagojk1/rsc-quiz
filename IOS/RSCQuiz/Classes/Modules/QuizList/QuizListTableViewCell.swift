//
//  QuizListTableViewCell.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit

class QuizListTableViewCell: UITableViewCell {

    private var isOpen: Bool?
    
    @IBOutlet weak var checkView: UIView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var timeLabel: UILabel!
    
    var quiz: Quiz? {
        willSet {
            nameLabel.text = newValue?.name
            descriptionLabel.text = newValue?.quizDescription
            timeLabel.text = "@\(newValue!.place!)"
            
            if newValue?.isOpen == true {
                checkView.backgroundColor = UIColor.green
            } else {
                checkView.backgroundColor = UIColor.red
            }
        }
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
}
