//
//  TeamTableViewCell.swift
//  RSCQuiz
//
//  Created by Nikola Majcen on 26/11/2016.
//  Copyright © 2016 Nikola Majcen. All rights reserved.
//

import UIKit

class TeamTableViewCell: UITableViewCell {

    @IBOutlet weak var teamNameLabel: UILabel!
    @IBOutlet weak var teamMembersNumberLabel: UILabel!
    
    var team: Team? {
        willSet {
            teamNameLabel.text = newValue?.name
            teamMembersNumberLabel.text = String(describing: newValue!.users!.count)
        }
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
