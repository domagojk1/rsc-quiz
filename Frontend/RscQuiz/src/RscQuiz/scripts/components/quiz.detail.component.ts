import {Component, Input} from '@angular/core';
import { Quiz } from '../classes/quiz'
import { Question } from '../classes/question'
import { QuizService } from '../services/quiz.service';
import { QuestionComponent } from './question.component';
import { QuestionDetailComponent } from './question.detail.component';

@Component({
    selector: 'quiz-detail',
    directives: [QuestionComponent, QuestionDetailComponent],
    templateUrl: '../views/quiz.detail.component.html'
})
export class QuizDetailComponent {
    @Input() quiz: Quiz;

    constructor(private quizService: QuizService) { }

    closeQuiz(): void {
        this.quizService.setCurrentQuiz(null);
    }

    getCurrentQuestion(): Question {
        return this.quizService.getCurrentQuestion();
    }

    openQuestion(question: Question): void {
        if (this.quiz.isOpen == false) {
            this.quizService.setCurrentQuestion(question);
        }
    }

    getNextQuestion() {
        this.quiz.questions.push({ text: "Next question" });
    }
}