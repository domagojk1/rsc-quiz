import {Component, Input} from '@angular/core';
import { Quiz} from '../classes/quiz'
import { QuizService } from '../services/quiz.service';
import { Team } from '../classes/team'

@Component({
    selector: 'teams',
    templateUrl: '../views/teams.component.html'
})
export class TeamsComponent {

    constructor(private quizService: QuizService) { }

    getTeams() : Team[] {
        return this.quizService.getTeams();
    }
}