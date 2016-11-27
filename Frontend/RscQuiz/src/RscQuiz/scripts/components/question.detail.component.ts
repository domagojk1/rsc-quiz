import {Component, Input} from '@angular/core';
import { Question} from '../classes/question'
import { QuizService } from '../services/quiz.service';

@Component({
    selector: 'question-detail',
    templateUrl: '../views/question.detail.component.html'
})
export class QuestionDetailComponent {
    @Input() question: Question;

    constructor(private quizService: QuizService) { }

    closeQuestion(): void {
        this.quizService.setCurrentQuestion(null);
    }
}