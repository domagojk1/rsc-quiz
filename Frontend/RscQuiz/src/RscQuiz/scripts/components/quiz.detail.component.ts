import {Component, Input} from '@angular/core';
import { Quiz } from '../classes/quiz'
import { Question } from '../classes/question'
import { QuizService } from '../services/quiz.service';
import { QuestionComponent } from './question.component';
import { QuestionDetailComponent } from './question.detail.component';
import { TeamsComponent } from './teams.component';

@Component({
    selector: 'quiz-detail',
    directives: [QuestionComponent, QuestionDetailComponent, TeamsComponent],
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

    startQuiz(): void {
        if (this.quizService.getTeams() != null && this.quizService.getTeams().length > 1) {
            this.quizService.startQuiz(this.quiz);
        }
    }

    canStartQuiz(): boolean {
        return this.quizService.getTeams() != null && this.quizService.getTeams().length > 1; 
    }

    isInProgress(): boolean {
        return this.quizService.getTeams() != null && this.quizService.getTeams().length > 1 && this.quiz.isOpen == false
    }

    getNextQuestion() {
        this.quizService.sendNextQuestion(this.quiz);
        this.quiz.questions.push({ text: "Next question" });
    }

    getNumberOfTeams(): number {
        return this.quizService.getTeams() != null ? this.quizService.getTeams().length : 0;
    }
}