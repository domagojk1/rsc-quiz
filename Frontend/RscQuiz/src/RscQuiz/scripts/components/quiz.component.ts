import {Component, Input} from '@angular/core';
import { Quiz} from '../classes/quiz'

@Component({
    selector: 'quiz',
    templateUrl: '../views/quiz.component.html'
})
export class QuizComponent {
    @Input() quiz: Quiz;
}