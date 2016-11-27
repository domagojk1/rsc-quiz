import {Component, Input, OnInit} from '@angular/core';
import {QuizComponent} from './quiz.component';
import {QuizDetailComponent} from './quiz.detail.component';
import { QuizService } from '../services/quiz.service';
import { Quiz } from '../classes/quiz'

@Component({
    selector: 'quizes',
    directives: [QuizComponent, QuizDetailComponent],
    templateUrl: '../views/quizes.component.html'
})
export class QuizesComponent implements OnInit {

    constructor(private quizService: QuizService) { }

    ngOnInit() {
        //this.quizService.getMyQuizes();
    }

    getQuizes(): Quiz[]{
        return this.quizService.getQuizes();
    }

    getCurrentQuiz(): Quiz {
        return  this.quizService.getCurrentQuiz();
    }

    openQuiz(quiz: Quiz): void {
        this.quizService.setCurrentQuiz(quiz);
    }
}