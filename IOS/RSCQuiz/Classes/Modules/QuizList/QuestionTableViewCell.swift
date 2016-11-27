//
//  QuestionTableViewCell.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 27/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit
import M13Checkbox

class QuestionTableViewCell: UITableViewCell {

    @IBOutlet weak var answerCheckbox: M13Checkbox!
    @IBOutlet weak var anwerTextLabel: UILabel!
    
    var answer: Answer? {
        willSet {
            if newValue?.checked == true {
                answerCheckbox.checkState = .checked
            } else {
                answerCheckbox.checkState = .unchecked
            }
            anwerTextLabel.text = newValue?.text!
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
