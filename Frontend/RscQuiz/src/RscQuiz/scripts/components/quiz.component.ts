import {Component, Input} from '@angular/core';
import { Quiz} from '../classes/quiz'
import { QuizService } from '../services/quiz.service';

@Component({
    selector: 'quiz',
    templateUrl: '../views/quiz.component.html'
})
export class QuizComponent {
    @Input() quiz: Quiz;

    constructor(private quizService: QuizService) { }

    openQuiz() {
        this.quiz.isOpen = true;
        this.quizService.openQuiz(this.quiz);
    }
}