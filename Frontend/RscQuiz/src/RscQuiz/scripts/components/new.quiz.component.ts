import {Component, Input} from '@angular/core';
import {QuizComponent} from './quiz.component';
import {QuizDetailComponent} from './quiz.detail.component';
import { QuizService } from '../services/quiz.service';
import { Quiz } from '../classes/quiz'
import { SuccessError } from '../interfaces/success.error';
import { GenericResponse } from '../interfaces/generic.response';

@Component({
    selector: 'new-quiz',
    directives: [QuizComponent, QuizDetailComponent],
    templateUrl: '../views/new.quiz.component.html'
})
export class NewQuizComponent {
    quiz: Quiz = new Quiz();

    constructor(private quizService: QuizService) { }

    addNewQuiz() {
        var successError: SuccessError = {
            onSuccess: (data: GenericResponse) => {
                if (data.status) {
                    console.log("added new quiz")
                }
            },
            onError: error => successError.onError(error)
        };
        this.quizService.addNewQuiz(this.quiz, successError);
    }
}